package tn.MITProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.MITProject.services.ILogService;

//import tn.MITProject.services.ILogService;

@RestController
@RequestMapping("/log")
public class LogController {
	
	@Autowired
	ILogService iLogService;

	// http://localhost:8081/mit/log/fire-agent/8
		@PutMapping("/fire-agent/{agent-id}")
		@ResponseBody
		public void fireAgent(@PathVariable("agent-id") Long agentId) {
		   iLogService.fireAgent(agentId);
		}
		
		
		// http://localhost:8081/mit/log/fire-expert/8
		@PutMapping("/fire-expert/{expert-id}")
		@ResponseBody
		public void fireExpert(@PathVariable("expert-id") Long expertId) {
		   iLogService.fireExpert(expertId);
		}
}
