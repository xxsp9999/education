package pers.xx.edu.vo;


import java.util.List;

import pers.xx.edu.entity.Module;

public class ListMenu{

	private Module module;// 一级菜单名

	private List<Module> subModule;// 二级菜单

	
	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public List<Module> getSubModule() {
		return subModule;
	}

	public void setSubModule(List<Module> subModule) {
		this.subModule = subModule;
	}

	public ListMenu() {

	}

	@Override
	public String toString() {
		return "SetMenu [module=" + module + ", subModule=" + subModule + "]";
	}

	public ListMenu(Module module, List<Module> subModule) {
		this.module = module;
		this.subModule = subModule;
	}

}
