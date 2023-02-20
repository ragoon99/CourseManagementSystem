package backend.Interfaces;

import java.util.HashMap;

import backend.Details.ModulesDetails;

public interface ModuleInterface {
	
	public boolean createModule(ModulesDetails module);
	public boolean updateModule(ModulesDetails module, String newCode);
	public boolean removeModule(String moduleCode);
	public HashMap<String, String> moduleList();
	public ModulesDetails getModulesDetails(String moduleCode);
	
}
