package controlP5;

import java.util.ArrayList;
import java.util.List;

/**
 * controlP5 is a processing gui library.
 * 
 * 2006-2015 by Andreas Schlegel
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2.1
 * of the License, or (at your option) any later version.
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General
 * Public License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307 USA
 * 
 * @author Andreas Schlegel (http://www.sojamo.de)
 * @modified ##date##
 * @version ##version##
 * 
 */
public class Group extends ControlGroup< Group > {


	private int _orientation = 0;
	//used space in both X and Y axis
	private int _usedSpace[] = new int[2];
	/**
	 * Convenience constructor to extend Group.
	 * 
	 * @example use/ControlP5extendController
	 */
	public Group( ControlP5 theControlP5 , String theName ) {
		this( theControlP5 , theControlP5.getDefaultTab( ) , theName , 0 , 0 , 99 , 9 );
		theControlP5.register( theControlP5.papplet , theName , this );
	}

	public Group( ControlP5 theControlP5 , ControllerGroup< ? > theParent , String theName , int theX , int theY , int theW , int theH ) {
		super( theControlP5 , theParent , theName , theX , theY , theW , theH );
	}

	public Group setOrientation( int theOrientation ) {
		this._orientation = theOrientation;
        return this;
	}
	public int getOrientation() {
		return _orientation;
	}

	public Group addUsedSpace( int x,int y ) {
		this._usedSpace[0] += x;
		this._usedSpace[1] += y;
        return this;
	}
	public Group setUsedSpace( int[] theUsedSpace ) {
		this._usedSpace = theUsedSpace;
        return this;
	};

	public void addChildHorizontally(ControllerInterface<?> controller) {

		int controllerWidth = controller.getWidth();
		if (_usedSpace[0] + controllerWidth > this.getWidth()) {

			_usedSpace[0] = 0;
			_usedSpace[1] += controller instanceof Group ? ((Group) controller).getBackgroundHeight() : controller.getHeight();
		}

		controller.setPosition(_usedSpace[0], _usedSpace[1]);
		controller.moveTo(this);
		_usedSpace[0] += controllerWidth;
	}


	public int[] getUsedSpace() {
		return _usedSpace;
	}

	public void didSetupLayout() {

	}

	public void clear(){
		List<String> namesToRemove = new ArrayList<>();
		for (int i = 0; i < controllers.size(); i++) {
			ControllerInterface<?> controller = controllers.get(i);
			namesToRemove.add(controller.getName());
		}
		for ( String name : namesToRemove) {
			cp5.remove(name);
		}
		setUsedSpace(new int[2]);
	}
}
