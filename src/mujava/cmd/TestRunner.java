package mujava.cmd;

import mujava.TestExecuter;
import mujava.test.*;


public class TestRunner {

  static final boolean CLASS_MODE = true;
	static final boolean TRADITIONAL_MODE = false;

	static TestResult runTest(String targetClassName, String testSetName, int timeout_secs, boolean mode){

    if((targetClassName!=null)&&(testSetName!=null)){
			try{
					TestExecuter test_engine = new TestExecuter(targetClassName);
					test_engine.setTimeOut(timeout_secs);

					// First, read (load) test suite class.
					test_engine.readTestSet(testSetName);

					TestResult test_result = new TestResult();
					if(mode==CLASS_MODE){
						test_result = test_engine.runClassMutants();
					}else{
						test_result = test_engine.runTraditionalMutants("");
					}
					return test_result;
			}catch(Exception e){
					System.err.println(e);
					return null;
			}
		}else{
				System.out.println(" [Error] Please check test target or test suite ");
				return null;
		}

	}

	static TestResult runClassTest(String targetClassName, String testSetName, int timeout_secs){
		return runTest(targetClassName,testSetName,timeout_secs,CLASS_MODE);
	}


	static TestResult runTraditionalTest(String targetClassName, String testSetName, int timeout_secs){
			return runTest(targetClassName,testSetName,timeout_secs,TRADITIONAL_MODE);
	}

}