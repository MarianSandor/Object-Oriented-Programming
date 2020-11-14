/** Car Parts interface
 *
 *  -defines the parts that can be replaced in the repair shop.
 *  -all the parts must implement this interface.
 *  -the current parts defined are: Doors, Engine, Gear Box, Seats, Tires and Windshield.
 *
 */
package car.parts;

public interface Part {
    public int getPrice();
    public String getId();
    public void setId(String id);
    public String getName();
}
