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
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import oshi.PlatformEnum;
import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;
import oshi.software.os.OperatingSystem.ProcessFiltering;
import oshi.software.os.OperatingSystem.ProcessSorting;
import oshi.util.FormatUtil;

/**
 * A demonstration of access to many of OSHI's capabilities
 */
public class PrintProcesses { // NOSONAR squid:S5786

	private static final Logger logger = LoggerFactory.getLogger(PrintProcesses.class);

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
		OperatingSystem os = si.getOperatingSystem();

		logger.info("Checking Processes...");
		printProcesses(os, hal.getMemory());

		StringBuilder output = new StringBuilder();
		for (String line : oshi) {
			output.append(line);
			if (line != null && !line.endsWith("\n")) {
				output.append('\n');
			}
		}
		logger.info("Printing Operating System and Hardware Info:{}{}", '\n', output);
	}

	private static void printProcesses(OperatingSystem os, GlobalMemory memory) {
		OSProcess myProc = os.getProcess(os.getProcessId());
		// current process will never be null. Other code should check for null here
		oshi.add(
				"My PID: " + myProc.getProcessID() + " with affinity " + Long.toBinaryString(myProc.getAffinityMask()));
		oshi.add("Processes: " + os.getProcessCount() + ", Threads: " + os.getThreadCount());
		// Sort by highest CPU
		List<OSProcess> procs = os.getProcesses(ProcessFiltering.ALL_PROCESSES, ProcessSorting.CPU_DESC, 5);
		oshi.add("   PID  %CPU %MEM       VSZ       RSS Name");
		for (int i = 0; i < procs.size(); i++) {
			OSProcess p = procs.get(i);
			oshi.add(String.format(" %5d %5.1f %4.1f %9s %9s %s", p.getProcessID(),
					100d * (p.getKernelTime() + p.getUserTime()) / p.getUpTime(),
					100d * p.getResidentSetSize() / memory.getTotal(), FormatUtil.formatBytes(p.getVirtualSize()),
					FormatUtil.formatBytes(p.getResidentSetSize()), p.getName()));
		}
		OSProcess p = os.getProcess(os.getProcessId());
		oshi.add("Current process arguments: ");
		for (String s : p.getArguments()) {
			oshi.add("  " + s);
		}
		oshi.add("Current process environment: ");
		for (Entry<String, String> e : p.getEnvironmentVariables().entrySet()) {
			oshi.add("  " + e.getKey() + "=" + e.getValue());
		}
	}
}