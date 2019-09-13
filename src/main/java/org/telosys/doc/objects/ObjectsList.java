package org.telosys.doc.objects;

import org.telosys.tools.generator.context.AttributeInContext;
import org.telosys.tools.generator.context.BeanValidation;
import org.telosys.tools.generator.context.Const;
import org.telosys.tools.generator.context.DatabaseInContext;
import org.telosys.tools.generator.context.DatabasesInContext;
import org.telosys.tools.generator.context.EmbeddedGenerator;
import org.telosys.tools.generator.context.EntityInContext;
import org.telosys.tools.generator.context.EnvInContext;
import org.telosys.tools.generator.context.FnInContext;
import org.telosys.tools.generator.context.ForeignKeyColumnInContext;
import org.telosys.tools.generator.context.ForeignKeyInContext;
import org.telosys.tools.generator.context.H2InContext;
import org.telosys.tools.generator.context.HtmlInContext;
import org.telosys.tools.generator.context.Java;
import org.telosys.tools.generator.context.JdbcFactoryInContext;
import org.telosys.tools.generator.context.JdbcInContext;
import org.telosys.tools.generator.context.JoinColumnInContext;
import org.telosys.tools.generator.context.Jpa;
import org.telosys.tools.generator.context.LinkAttributesPairInContext;
import org.telosys.tools.generator.context.LinkInContext;
import org.telosys.tools.generator.context.Loader;
import org.telosys.tools.generator.context.ModelInContext;
import org.telosys.tools.generator.context.ProjectInContext;
import org.telosys.tools.generator.context.Target;
import org.telosys.tools.generator.context.Today;
import org.telosys.tools.generator.context.ValuesInContext;

/**
 * Provides a list of classes for all objects defined in the generator context <br>
 * This list must be updated if an object is added or removed in the generator context.<br>
 * 
 * @author L. Guerin
 *
 */
public class ObjectsList {

	private ObjectsList() {}

	private static final Class<?>[] templatesObjectsClasses = new Class<?>[] {
		Const.class,
		EmbeddedGenerator.class,
		FnInContext.class,
		// GenerationInContext.class, // ver 2.1.0 // removed in v 3.0.0
		Java.class, // ver 2.0.7
		Jpa.class, // ver 2.0.7
		BeanValidation.class, // ver 2.0.7
		//JavaBeanClass.class,
		EntityInContext.class, // replaces JavaBeanClass.class ( ver 2.1.0 )
		AttributeInContext.class,
		ForeignKeyInContext.class, // ver 2.0.7
		ForeignKeyColumnInContext.class, // ver 2.0.7
		JoinColumnInContext.class, // ver 2.1.0
		LinkInContext.class,
		LinkAttributesPairInContext.class, // ver 2.1.0
		Loader.class,
		//Model.class, // ver 2.0.7
		ModelInContext.class, // ver 2.1.0
		DatabasesInContext.class, // ver 2.1.0
		DatabaseInContext.class, // ver 2.1.0
		//ProjectConfiguration.class,
		ProjectInContext.class, // ver 2.1.0
		Target.class,
		Today.class,
		EnvInContext.class, // ver 2.1.0
		JdbcInContext.class, // ver 2.1.1
		JdbcFactoryInContext.class, // ver 2.1.1
		H2InContext.class, // ver 2.1.1
		
		HtmlInContext.class, // v 3.0.0
		ValuesInContext.class // v 3.0.0
	};

	public static final Class<?>[] getObjectsClasses() {
		return templatesObjectsClasses ;
	}

}
