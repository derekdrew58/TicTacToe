package test.java;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import junit.framework.*;
import main.java.TicTacToe;
import main.java.components.UserInput;

public class TicTacToeTests extends TestCase {
	
	   protected void setUp(){
		   
	   }

	   @SuppressWarnings("deprecation")
	   public void testValidXOrO() {
		   InputStream stdin = System.in;
	       System.setIn(new ByteArrayInputStream("x".getBytes()));
	       ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	       PrintStream ps = new PrintStream(byteArrayOutputStream);
	       PrintStream stdout = System.out;
	       System.setOut(ps);
	       TicTacToe.main(new String[0]);
	       System.setIn(stdin);
	       System.setOut(stdout);
	       String outputText = byteArrayOutputStream.toString();
	       String key = "output:";
	       String output = outputText.substring(outputText.indexOf(key) + key.length()).trim();
	       boolean goodToGo = output.contains("Great, let's get started.");
	       Assert.assertEquals(true, goodToGo);
	   }
	   
	   @SuppressWarnings("deprecation")
	   public void testInValidXOrO() {
		   InputStream stdin = System.in;
	       System.setIn(new ByteArrayInputStream("afoijadsif,180313284".getBytes()));
	       ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	       PrintStream ps = new PrintStream(byteArrayOutputStream);
	       PrintStream stdout = System.out;
	       System.setOut(ps);
	       TicTacToe.main(new String[0]);
	       System.setIn(stdin);
	       System.setOut(stdout);
	       String outputText = byteArrayOutputStream.toString();
	       String key = "output:";
	       String output = outputText.substring(outputText.indexOf(key) + key.length()).trim();
	       boolean goodToGo = output.contains("Great, let's get started.");
	       Assert.assertEquals(false, goodToGo);
	   }
	   
	   @SuppressWarnings("deprecation")
	   public void testFullGameWithValidCoordinates() {
		   InputStream stdin = System.in;
	       System.setIn(new ByteArrayInputStream("x\n0,0\0,1\n0,2\n1,0\n1,1\n1,2\n2,0\n2,1\n2,2".getBytes()));
	       ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	       PrintStream ps = new PrintStream(byteArrayOutputStream);
	       PrintStream stdout = System.out;
	       System.setOut(ps);
	       TicTacToe.main(new String[0]);
	       System.setIn(stdin);
	       System.setOut(stdout);
	       String outputText = byteArrayOutputStream.toString();
	       String key = "output:";
	       String output = outputText.substring(outputText.indexOf(key) + key.length()).trim();
	       System.out.println(output);
	       boolean goodToGo = output.contains("Thank you for playing!");
	       Assert.assertEquals(true, goodToGo);
	   }
	   
	   @SuppressWarnings("deprecation")
	   public void testFullGameWithInValidCoordinates() {
		   InputStream stdin = System.in;
	       System.setIn(new ByteArrayInputStream("x\n0,0\na,1\n0,10\n1,-1\n3,1\n4,2\n5,b\ny,1\n2,7".getBytes()));
	       ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	       PrintStream ps = new PrintStream(byteArrayOutputStream);
	       PrintStream stdout = System.out;
	       System.setOut(ps);
	       TicTacToe.main(new String[0]);
	       System.setIn(stdin);
	       System.setOut(stdout);
	       String outputText = byteArrayOutputStream.toString();
	       String key = "output:";
	       String output = outputText.substring(outputText.indexOf(key) + key.length()).trim();
	       boolean openToRetrying = output.contains("Would you like to play again?");
	       Assert.assertEquals(false, openToRetrying);
	   }
	   
/*	   @SuppressWarnings("deprecation")
	   public void testValidWouldYouLikeToPlayAgain() {
		   UserInput userInput = new UserInput();
		 
	     
	       
	       boolean result = userInput.askForYesOrNo();
	       userInput.getScanner().close();
	       System.out.println(result);
		   Assert.assertEquals(true, result);
		   
	   }*/
	   
	   
}
