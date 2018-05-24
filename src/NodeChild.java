
/**
 * TODO Put here a description of what this class does.
 *
 * @author Server.
 *         Created May 23, 2018.
 */
public class NodeChild {
	Region region;
	Node child;
	
	
	public NodeChild(Region r, Node c) {
		region = r;
		child = c;
	}
	
	public Node getChild() { return child; }
	public Region getRegion() { return region; }
	
	public NodeChild compareByRegion(Region r) {
		if (region == r) return this;
		 return null;
	}
	
	public NodeChild compareByNode(Node c) {
		if (child == c) return this;
		 return null;
	}
	
	public String toString()  { return "\n\tNC: ("+region.min_x+", "+region.min_y+", "+region.max_x+", "+region.max_y+") \n{"+child+"}"; }
}
