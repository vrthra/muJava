////////////////////////////////////////////////////////////////////////////
// Module : JMutationLoader.java
// Author : Ma, Yu-Seung
// COPYRIGHT 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED.
////////////////////////////////////////////////////////////////////////////

package mujava.test;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

import mujava.MutationSystem;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED </p>
 * @author Yu-Seung Ma
 * @author Nan Li modified on 06/30/2013 for adding getResource(String)
 * @version 1.0
  */

public class JMutationLoader extends ClassLoader{

  String mutant_name;
  boolean tt = false;

  public JMutationLoader()
  {
    super(null);
  }

  public JMutationLoader(String dir)
  {
    super(null);
    mutant_name = dir;
  }


  public synchronized Class loadTestClass(String name) throws ClassNotFoundException{
    Class result;
    try{
      // Try to load mutant class
      byte[] data = getClassData(name,MutationSystem.TESTSET_PATH);
      result = defineClass(name, data,0,data.length);
      if(result==null){
        throw new ClassNotFoundException(name);
      }
    }catch(IOException e){
      throw new ClassNotFoundException();
    }
    return result;
  }

  public synchronized Class loadClass(String name) throws ClassNotFoundException
  {
    // See if type has already been loaded by
    // this class loader
    Class result = findLoadedClass(name);
    if (result != null){
      // Return an already-loaded class
      return result;
    }

    try{
      result = findSystemClass(name);
      return result;
    } catch (ClassNotFoundException e){
      // keep looking
    }

    try{
      byte[] data=null;
      try{
        try{
          int start_index = name.lastIndexOf(".");
          if(start_index>=0){
            String nameWithNoPackage = name.substring(start_index+1,name.length());
            data = getClassData(nameWithNoPackage,MutationSystem.MUTANT_PATH+"/"+mutant_name);
          }else{
            data = getClassData(name,MutationSystem.MUTANT_PATH+"/"+mutant_name);
          }
        }catch(FileNotFoundException e){
          data = getClassData(name,MutationSystem.CLASS_PATH);
        }
      }catch(FileNotFoundException e){
        data = getClassData(name,MutationSystem.TESTSET_PATH);
      }

      result = defineClass(name, data,0,data.length);
      if(result==null){
         throw new ClassNotFoundException(name);
      }
      return result;

    }catch(IOException e){
      throw new ClassNotFoundException();
    }
  }



  private byte[] getClassData(String name,String directory) throws FileNotFoundException,IOException
  {
    String filename = name.replace ('.', File.separatorChar) + ".class";

    // Create a file object relative to directory provided
    File f = new File (directory, filename);

     // Get stream to read from
    FileInputStream fis = new FileInputStream(f);

    BufferedInputStream bis = new BufferedInputStream(fis);
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    try {
      int c = bis.read();
      while (c != -1) {
        out.write(c);
        c = bis.read();
      }
    } catch (IOException e) {
      return null;
    }
    return out.toByteArray();
  }
  
  /**
   * Overrides getResource (String) to get non-class files including resource bundles from property files
   */
  @Override
  public URL getResource(String name){
	  URL url = null;
	  File resource = new File(MutationSystem.CLASS_PATH, name);
	  if(resource.exists()){
		  try {
			return resource.toURI().toURL();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  return url;
  }
}


