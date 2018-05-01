package com.example.util;

import com.example.exception.IllegalArgumentException;
import com.example.exception.InsufficientArgumentException;
import com.example.util.Utility;
import org.junit.Assert;
import org.junit.Test;
import com.example.request.InputParamType;
import com.example.request.InputParams;

public class UtilityTest {

	@Test public void TestIgnorePatternMatch() {
		String textIgnorePatternMatch = "Anything";
		Assert.assertTrue(Utility.matchSelectionPattern(textIgnorePatternMatch, false));
	}

	@Test public void TestPatternMatchWithoutNewLine() {
		String textWithoutNewLine = "A sample text without new line character";
		Assert.assertFalse(Utility.matchSelectionPattern(textWithoutNewLine, true));
	}

	@Test public void TestPatternMatchWithSingleNewLine() {
		String textWithSingleNewLine = "A sample text with \n new line character not at the end";
		Assert.assertFalse(Utility.matchSelectionPattern(textWithSingleNewLine, true));
	}

	@Test public void TestPatternMatchWithSingleNewLineAtEnd() {
		String textWithSingleNewLineAtEnd = "A sample text with a new line character at the end\n";
		Assert.assertTrue(Utility.matchSelectionPattern(textWithSingleNewLineAtEnd, true));
	}

	@Test public void TestPatternMatchWithMultipleNewLineAtEnd() {
		String textWithSingleNewLineAtEnd = "A sample text with multiple \nnew line character at the end\n";
		Assert.assertTrue(Utility.matchSelectionPattern(textWithSingleNewLineAtEnd, true));
	}

	@Test public void TestParseInputArguments1(){
		String [] inputArgs = {"-config.path=/conf", "-access.token=xyz", "-page.id=123"};
		InputParams inputParams = Utility.parseArguments(inputArgs);
		Assert.assertEquals("/conf", inputParams.getParams().get(InputParamType.CONFIG_PATH));
		Assert.assertEquals("xyz", inputParams.getParams().get(InputParamType.ACCESS_TOKEN));
		Assert.assertEquals("123", inputParams.getParams().get(InputParamType.PAGE_ID));
		Assert.assertEquals(InputParams.FALSE_VALUE, inputParams.getParams().get(InputParamType.ENABLE_FILTER));
	}

	@Test public void TestParseInputArguments2(){
		String [] inputArgs = {"-config.path=/conf", "-enable.filter=true", "-page.id=123"};
		InputParams inputParams = Utility.parseArguments(inputArgs);
		Assert.assertEquals("/conf", inputParams.getParams().get(InputParamType.CONFIG_PATH));
		Assert.assertEquals(InputParams.DEFAULT_VALUE, inputParams.getParams().get(InputParamType.ACCESS_TOKEN));
		Assert.assertEquals("123", inputParams.getParams().get(InputParamType.PAGE_ID));
		Assert.assertEquals("true", inputParams.getParams().get(InputParamType.ENABLE_FILTER));
	}

	@Test public void TestParseInputArguments3(){
		String [] inputArgs = {"-config.path=/conf", "-enable.filter=true", "-page.id=123" ,"-access.token=xyz"};
		InputParams inputParams = Utility.parseArguments(inputArgs);
		Assert.assertEquals("/conf", inputParams.getParams().get(InputParamType.CONFIG_PATH));
		Assert.assertEquals("xyz", inputParams.getParams().get(InputParamType.ACCESS_TOKEN));
		Assert.assertEquals("123", inputParams.getParams().get(InputParamType.PAGE_ID));
		Assert.assertEquals("true", inputParams.getParams().get(InputParamType.ENABLE_FILTER));
	}

	@Test (expected = IllegalArgumentException.class)
	public void TestParseInputIllegalArguments(){
		String [] inputArgs = {"-conf.path=/conf", "-access.token=xyz", "-page.id=123"};
		Utility.parseArguments(inputArgs);
	}

	@Test (expected = IllegalArgumentException.class)
	public void TestParseMissingMandatoryArguments(){
		String [] inputArgs = {"-access.token=xyz", "-page.id=123"};
		Utility.parseArguments(inputArgs);
	}

	@Test (expected = InsufficientArgumentException.class)
	public void TestParseInsufficientArguments(){
		String [] inputArgs = {"-page.id=123"};
		Utility.parseArguments(inputArgs);
	}

	@Test (expected = IllegalArgumentException.class)
	public void TestParseIllegalValue1(){
		String [] inputArgs = {"-config.path=/conf", "-enable.filter=invalid", "-page.id=123"};
		Utility.parseArguments(inputArgs);
	}

	@Test (expected = IllegalArgumentException.class)
	public void TestParseIllegalValue2(){
		String [] inputArgs = {"-config.path=/conf", "-enable.filter=invalid", "-page.id=123", "-access.token=xyz"};
		Utility.parseArguments(inputArgs);
	}
}
