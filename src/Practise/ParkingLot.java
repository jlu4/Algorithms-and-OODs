package Practise;

import java.util.Iterator;
import java.util.Vector;

public class ParkingLot {
     boolean isFull;
     boolean isEmpty;
     int totalNumSpaces = 10;
     Vector<ParkingSpace> vacantSpaces = new Vector<ParkingSpace>();
     Vector<ParkingSpace> fullSpaces = new Vector<ParkingSpace>();
     public ParkingSpace findVacant(ParkingType type) {
    	 if (!isFull()) {
    		 Iterator<ParkingSpace> it = vacantSpaces.iterator();
    		 while (it.hasNext()) {
    			 ParkingSpace space = it.next();
    			 if (space.isVacant && space.parkingType == type) {
    			      return space;
    			 }
    		 }
    	 }
    	 return null;
     }
     
     public void Parking(ParkingSpace space) {
    	     ParkingSpace findspace = findVacant(space.parkingType);
    	     if (space.isVacant) {
    	    	 space.parkingType = findspace.parkingType;
    	    	 space.isVacant = false;
    	    	 fullSpaces.add(space);
    	    	 vacantSpaces.remove(space);
    	    	 if (fullSpaces.size() == totalNumSpaces) {
    	    		 isFull = true;
    	    		 isEmpty = false;
    	    	 }
    	     } else
    	    	 return;
     }
     
     
     public boolean isFull() {
    	 return isFull;
     }
     
     public boolean isEmpty() {
    	 return isEmpty;
     }
}
