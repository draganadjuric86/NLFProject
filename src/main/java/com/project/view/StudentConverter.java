package com.project.view;

import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.WeakHashMap;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import com.project.model.Book;
import com.project.model.Student;

@Named
	@ApplicationScoped
	@FacesConverter(value = "studentConverter")
	public class StudentConverter implements Converter<Student> {

		private static Map<Student, String> entities = new WeakHashMap<Student, String>();

		@Override
		public String getAsString(FacesContext context, UIComponent component, Student entity) {
			synchronized (entities) {
				if (!entities.containsKey(entity)) {
					String uuid = UUID.randomUUID().toString();
					entities.put(entity, uuid);
					return uuid;
				} else {
					return entities.get(entity);
				}
			}
		}

		@Override
		public Student getAsObject(FacesContext context, UIComponent component, String uuid) {
			for (Entry<Student, String> entry : entities.entrySet()) {
				if (entry.getValue().equals(uuid)) {
					return entry.getKey();
				}
			}
			return null;
		}
}
