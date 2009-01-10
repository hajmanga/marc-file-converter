package ppa.marc;

public interface ArgumentParser {

	Configuration parseArgument(String[] arguments) throws ParameterException;

	String getHelp();
	
}
