package controller;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import controller.command.Command;
import controller.command.SokobanCommand;
import model.Model;
import server.MyClient;
import view.View;

public class CommandClassLoader {

	public void initMap(Map<String, Command> map, View view, Model model, Controller controller, MyClient client) {
		Set<Class<? extends SokobanCommand>> list = commandSet("controller.command");
		list.forEach(commandClass -> {
			try {
				SokobanCommand command = commandClass
						.getDeclaredConstructor(View.class, Model.class, Controller.class, MyClient.class)
						.newInstance(view, model, controller, client);
				map.put(command.getKey(), command);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		});

	}

	private Set<Class<? extends SokobanCommand>> commandSet(String packagePath) {
		List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
		classLoadersList.add(ClasspathHelper.contextClassLoader());
		classLoadersList.add(ClasspathHelper.staticClassLoader());

		Reflections reflections = new Reflections(new ConfigurationBuilder()
				.setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner())
				.setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
				.filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(packagePath))));
		Set<Class<? extends SokobanCommand>> classes = reflections.getSubTypesOf(SokobanCommand.class);
		return classes;
	}

}
