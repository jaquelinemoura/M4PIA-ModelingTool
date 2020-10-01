package m4pia.design;

import javax.annotation.Generated;

import org.eclipse.emf.ecore.EObject;

import M4PIAmetamodel.Equipment;
import M4PIAmetamodel.Attribute;
import M4PIAmetamodel.BasicType;
/**
 * The services class used by VSM.
 */
import m4pia.design.Activator;;

public class Services {

	/**
	 * See
	 * http://help.eclipse.org/neon/index.jsp?topic=%2Forg.eclipse.sirius.doc%2Fdoc%2Findex.html&cp=24
	 * for documentation on how to write service methods.
	 */
	public EObject myService(EObject self, String arg) {
		// TODO Auto-generated code
		return self;
	}

	public String getAttribute(Equipment equipment) {
		java.lang.String data = "";
		for (Attribute at : equipment.getAttr()) {
			if (at instanceof BasicType) {
				data = data+ "\n"+at.getName();
			}
		}
		return data;
	}
}
