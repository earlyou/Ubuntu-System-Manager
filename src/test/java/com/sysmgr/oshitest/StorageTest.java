package com.sysmgr.oshitest;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import oshi.SystemInfo;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HWPartition;
import oshi.hardware.HardwareAbstractionLayer;

@SpringBootTest
class StorageTest {
	
	@Test
	void contextLoads() {
		SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
//        OperatingSystem os = si.getOperatingSystem();
		
        List<HWDiskStore> list = hal.getDiskStores();
        
        System.out.println("Disks:");
        for (HWDiskStore disk : list) {
        	System.out.println("disk.toString: " + disk.toString());

            List<HWPartition> partitions = disk.getPartitions();
            for (HWPartition part : partitions) {
            	System.out.println(" |-- " + part.toString());
            }
        }
	}
}

