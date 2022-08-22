package m4pia.design;

import org.eclipse.emf.ecore.EObject;

import org.apache.commons.io.FilenameUtils;
import M4PIAmetamodel.Equipment;
import M4PIAmetamodel.EquipmentType;
import M4PIAmetamodel.Real;
import M4PIAmetamodel.Integer;
import M4PIAmetamodel.Boolean;
import M4PIAmetamodel.Attribute;
import M4PIAmetamodel.BasicType;

/**
 * The services class used by VSM.
 */
public class Services {

	static String ICONS_PATH = "/m4pia.design/images/shapes/";
	static String DEFAULT_ICON = "Cube-Blue.svg";	

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
		
		String defaultIcon = ICONS_PATH + DEFAULT_ICON;
		
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
			return ICONS_PATH + equipmentIcon;
		}

		return defaultIcon;
	}
   
	
	public String getEquipmentImage(EquipmentType equipmentType) {
		return getEquipmentImage(equipmentType.getBase());
	}
	/*********************** ATTRIBUTE *******************************/
 
	/**
	 * This method return a formated label to display with the Equipment details like notation interval
	 *  Lower and Upper limits to Real and Integer e.g. * [Lower,Upper], TRUE/FALSE to boolean
	 * @param attribute
	 * @return label
	 */
	public String getAttributeDetails(Attribute attribute) {		
		String label = new String();
		if (attribute instanceof BasicType) {
			if (attribute instanceof Real) {
				Real real = (Real) attribute;
				if (real.getLower() != null || real.getUpper() != null) {
					label += real.getLower() != null ? " ["+real.getLower() : " ]-";
					label += " , ";
					label += real.getUpper() != null ? real.getUpper()+"]" : "+[";
				}else {
					label += " [" + real.getLower() + " , " + real.getUpper() + "]";
				}
				if (real.getDefault() != null ) {
					label += " default: " + real.getDefault() + " ";
					label += real.getDisplayunit() != null ? real.getDisplayunit() : "";													
				}			
			} else if (attribute instanceof Integer) {
				Integer integer = (Integer) attribute;
				boolean hasLowwer = String.valueOf(integer.getLower()) != null;
				boolean hasUpper = String.valueOf(integer.getUpper()) != null;
				if (hasLowwer && hasUpper) {
					label = integer.getDefault() + " [" + integer.getLower() + " , " + integer.getUpper() + "]";					
				}else {
					label += hasLowwer ? " ["+integer.getLower() : " ]-";
					label += " , ";
					label += hasUpper ? integer.getUpper()+ "]" : "+[";
				}

			} else if (attribute instanceof Boolean) {
				Boolean bool = (Boolean) attribute;
				if (bool.getDefault() != null) {
					label = bool.getDefault() ? "TRUE" : "FALSE";
				}
			} else if (attribute instanceof M4PIAmetamodel.String) {
				M4PIAmetamodel.String str = (M4PIAmetamodel.String) attribute;
				if (str.getDefault() != null) {
					label = str.getDefault();
				}
			}
		} else {
			EquipmentType equipmentType = (EquipmentType) attribute;
			label = attribute.getName() + ": " + equipmentType.getBase().getName();
		}

		return label;
	}
	

}
