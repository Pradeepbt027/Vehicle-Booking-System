package com.eikona.tech.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eikona.tech.dto.ContainerDto;
import com.eikona.tech.dto.PaginationDto;
import com.eikona.tech.entity.Container;
import com.eikona.tech.service.ContainerService;

@Controller
public class ContainerController {

	@Autowired
	private ContainerService containerService;

	
	@GetMapping({"/container","/"})
	@PreAuthorize("hasAuthority('container_view')")
	public String listContainer(Model model) {
		model.addAttribute("pageNo", 1);
		return "container/container_list";
	}
	
	@GetMapping("/container/{page}")
	@PreAuthorize("hasAuthority('container_view')")
	public String listContainerPage(Model model,@PathVariable(value = "page") long page) {
		model.addAttribute("pageno", page);
		return "container/container_list";
	}

	@GetMapping("/container/new")
	@PreAuthorize("hasAuthority('container_create')")
	public String newContainer(Model model) {

		ContainerDto containerDto = new ContainerDto();
		model.addAttribute("container", containerDto);
		model.addAttribute("title", "Add Container Details");
		return "container/container_new";
	}
	
	@PostMapping("/get/container-details")
	@PreAuthorize("hasAuthority('container_create')")
	public String fetchContainerDetails(@ModelAttribute("container") ContainerDto containerDto,Model model) {
		
		// call the govt. rest api and get the Container obj.
		
		Container container = new Container();
		container.setBoeNo(containerDto.getBoeNo());
		container.setContainerNo(containerDto.getContainerNo());
		container.setType(containerDto.getType());
		container.setSealNo(containerDto.getSealNo());
		container.setSize("2200");
		container.setWeight("1850");
		container.setVoyageNo("V.284");
		container.setOperator("ADM");
		container.setCurrentStatus("Ready For Booking");
		
		model.addAttribute("container", container);
		model.addAttribute("title", "Add Container Details");
		return "container/container_add";
		
	}

	@PostMapping("/container/add")
	@PreAuthorize("hasAuthority('container_create')")
	public String saveContainer(@ModelAttribute("container") Container container, String title,Model model) {

			if (null == container.getId())
				containerService.save(container);
			else {
				Container containerObj = containerService.getById(container.getId());
				container.setCreatedBy(containerObj.getCreatedBy());
				container.setCreatedDate(containerObj.getCreatedDate());
				containerService.save(container);
			}
			
				return "redirect:/container";
	
	}

	@GetMapping("/container/view/{id}/{pageno}")
	@PreAuthorize("hasAuthority('container_view')")
	public String viewContainer(@PathVariable(value = "id") long id, @PathVariable(value = "pageno") int pageno, Model model) {
		
		Container container = containerService.getById(id);
		model.addAttribute("container", container);
		model.addAttribute("title", "Container Details");
		model.addAttribute("pageno", pageno);
		return "container/container_view";
	}


	@RequestMapping(value = "/api/search/container", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('container_view')")
	public @ResponseBody PaginationDto<Container> searchContainer(String containerNo,String boeNo,String operator,String type, int pageno,
			String sortField,String sortDir) {
		
		PaginationDto<Container> dtoList = containerService.searchByField(containerNo,boeNo,operator,type, pageno, sortField, sortDir);
		return dtoList;
	}
}
