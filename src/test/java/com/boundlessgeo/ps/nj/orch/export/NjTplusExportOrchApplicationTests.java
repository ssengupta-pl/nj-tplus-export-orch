package com.boundlessgeo.ps.nj.orch.export;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;

import com.boundlessgeo.ps.nj.orchestration.NjTplusApplication;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = NjTplusApplication.class)
@WebAppConfiguration
public class NjTplusExportOrchApplicationTests {

	@Test
	public void contextLoads() {
	}

}
