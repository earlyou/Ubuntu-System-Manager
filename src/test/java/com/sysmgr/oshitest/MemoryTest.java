package com.sysmgr.oshitest;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.PhysicalMemory;
import oshi.hardware.VirtualMemory;

@SpringBootTest
class MemoryTest {
	
	@Test
	void contextLoads() {
		SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
//        OperatingSystem os = si.getOperatingSystem();
		
        GlobalMemory memory = hal.getMemory();
        
        // ���� �޸�
        System.out.println("Physical Memory: \n " + memory.toString());
        VirtualMemory vm = memory.getVirtualMemory();
        
        // ���� �޸�(?)
        System.out.println("Virtual Memory: \n " + vm.toString());
        List<PhysicalMemory> pmList = memory.getPhysicalMemory();
        
        // ���� �� �޸� ����
        if (!pmList.isEmpty()) {
        	System.out.println("Physical Memory: ");
            for (PhysicalMemory pm : pmList) {
            	System.out.println(" " + pm.toString());
            }
        }
	}
}

