package com.sysmgr.oshitest;

import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import oshi.SystemInfo;
import oshi.software.os.OSSession;
import oshi.software.os.OperatingSystem;
import oshi.util.FormatUtil;

@SpringBootTest
class OSTest {
	
	@Test
	void contextLoads() {
		SystemInfo si = new SystemInfo();
		OperatingSystem os = si.getOperatingSystem();
		
		String osinfo = String.valueOf(os);
		System.out.println("OS info: " + osinfo);
		
		Instant boottime = Instant.ofEpochSecond(os.getSystemBootTime());
		System.out.println("Boot Time: " + boottime);
		
		String uptime = FormatUtil.formatElapsedSecs(os.getSystemUptime());
		System.out.println("Uptime: " + uptime);
		
		boolean oselevated = os.isElevated();
		System.out.println("Running with" + (oselevated ? "" : "out") + " elevated permissions.");
		
		for (OSSession s : os.getSessions()) {
            System.out.println("	" + s.toString());
        }
	}
}

