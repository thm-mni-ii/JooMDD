package de.thm.icampus.joomdd.ejsl.ui.wizard;

public class EJSLProjectInfo extends org.eclipse.xtext.ui.wizard.DefaultProjectInfo {
	public String projectFile(){
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
				+ "		<projectDescription>"
				+ "		<name>" + getProjectName() + "</name>\n"
				+ "		<comment></comment>\n"
				+ "		<projects>\n"
				+ "		</projects>\n"
				+ "		<buildSpec>\n"
				+ "			<buildCommand>\n"
				+ "				<name>org.eclipse.xtext.ui.shared.xtextBuilder</name>\n"
				+ "				<arguments>\n"
				+ "				</arguments>\n"
				+ "			</buildCommand>\n"
				+ "			<buildCommand>\n"
				+ "				<name>org.eclipse.jdt.core.javabuilder</name>\n"
				+ "				<arguments>\n"
				+ "				</arguments>\n"
				+ "			</buildCommand>\n"
				+ "		</buildSpec>\n"
				+ "		<natures>\n"
				+ "			<nature>org.eclipse.jdt.core.javanature</nature>\n"
				+ "			<nature>org.eclipse.xtext.ui.shared.xtextNature</nature>\n"
				+ "		</natures>\n"
				+ "	</projectDescription>";

	}
	public String classpathFile(){
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
				+ "<classpath>\n"
				+ "	<classpathentry kind=\"src\" path=\"src\"/>\n"
				+ "	<classpathentry kind=\"con\" path=\"org.eclipse.jdt.launching.JRE_CONTAINER/org.eclipse.jdt.internal.debug.ui.launcher.StandardVMType/JavaSE-1.8\"/>\n"
				+ "	<classpathentry kind=\"output\" path=\"bin\"/>\n"
				+ "</classpath>";
	}
}
