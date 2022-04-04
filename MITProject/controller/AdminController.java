package tn.MITProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.MITProject.entities.Admin;
import tn.MITProject.services.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;

	// http://localhost:8081/mit/admin/retrieve-all-admins
	@GetMapping("/retrieve-all-admins")
	@ResponseBody
	public List<Admin> getAdmins() {
	List<Admin> listAdmins = adminService.retrieveAllAdmins();
	return listAdmins;
	}
	
	// http://localhost:8081/mit/admin/retrieve-admin/8
	@GetMapping("/retrieve-admin/{admin-id}")
	@ResponseBody
	public Admin retrieveAdmin(@PathVariable("admin-id") Long adminId) {
	return adminService.retrieveAdmin(adminId);
	}

	// http://localhost:8081/mit/admin/add-admin
	@PostMapping("/add-admin")
	@ResponseBody
	public Admin addAdmin(@RequestBody Admin a)
	{
	Admin admin = adminService.addAdmin(a);
	return admin;
	}
	

	// http://localhost:8081/mit/admin/modify-admin
	@PutMapping("/modify-admin")
	@ResponseBody
	public Admin modifyAdmin(@RequestBody Admin admin) {
	return adminService.updateAdmin(admin);
	}
	
	// http://localhost:8081/Achat/admin/remove-admin/{admin-id}
	@DeleteMapping("/remove-admin/{admin-id}")
	@ResponseBody
	public void removeAdmin(@PathVariable("admin-id") Long adminId) {
	adminService.deleteAdmin(adminId);
	}
	
	

}
