package m4pia.design;

import org.eclipse.emf.ecore.EObject;

import org.apache.commons.io.FilenameUtils;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import M4PIAmetamodel.Equipment;
import M4PIAmetamodel.EquipmentType;
import M4PIAmetamodel.Real;
import M4PIAmetamodel.Integer;
import M4PIAmetamodel.Boolean;
import M4PIAmetamodel.StringOption;
import M4PIAmetamodel.Attribute;
import M4PIAmetamodel.BasicType;

/**
 * The services class used by VSM.
 */
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

	/*********************** EQUIPMENT *******************************/

	/**
	 * Method to get a valid equipment's icon path
	 * 
	 * @param equipment
	 * @return String equipment's icon (valid) else default icon path
	 */
	public String getEquipmentImage(Equipment equipment) {
		String defaultIcon = "/m4pia.design/icons/Equipment.svg";

		String equipmentIcon = equipment.getIcon();
		if (equipmentIcon == null || equipmentIcon.isEmpty()) {
			return defaultIcon;
		}

		String fileExtension = FilenameUtils.getExtension(equipmentIcon);
		if (fileExtension == null || fileExtension.isEmpty()) {
			return defaultIcon;
		}

		if (fileExtension.compareToIgnoreCase("png") == 0 || fileExtension.compareToIgnoreCase("svg") == 0
				|| fileExtension.compareToIgnoreCase("jpg") == 0) {
			System.err.println("Format ok: " + fileExtension + " " + equipmentIcon);
			return equipmentIcon;
		}

		return defaultIcon;
	}

}
