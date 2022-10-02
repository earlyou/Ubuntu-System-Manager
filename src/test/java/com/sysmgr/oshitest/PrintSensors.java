/*
 * MIT License
 *
 * Copyright (c) 2016-2022 The OSHI Project Contributors: https://github.com/oshi/oshi/graphs/contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.sysmgr.oshitest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import oshi.PlatformEnum;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.Sensors;

/**
 * A demonstration of access to many of OSHI's capabilities
 */
public class PrintSensors { // NOSONAR squid:S5786

	private static final Logger logger = LoggerFactory.getLogger(PrintSensors.class);

	static List<String> oshi = new ArrayList<>();

	/**
	 * Test that this platform is implemented..
	 */
	@Test
	void testPlatformEnum() {
		assertThat("Unsupported OS", SystemInfo.getCurrentPlatform(), is(not(PlatformEnum.UNKNOWN)));
		// Exercise the main method
		main(null);
	}

	/**
	 * The main method, demonstrating use of classes.
	 *
	 * @param args the arguments (unused)
	 */
	public static void main(String[] args) {

		logger.info("Initializing System...");
		SystemInfo si = new SystemInfo();

		HardwareAbstractionLayer hal = si.getHardware();
//		OperatingSystem os = si.getOperatingSystem();

		logger.info("Checking Sensors...");
		printSensors(hal.getSensors());

		StringBuilder output = new StringBuilder();
		for (String line : oshi) {
			output.append(line);
			if (line != null && !line.endsWith("\n")) {
				output.append('\n');
			}
		}
		logger.info("Printing Operating System and Hardware Info:{}{}", '\n', output);
	}

	private static void printSensors(Sensors sensors) {
		oshi.add("Sensors: " + sensors.toString());
	}
}