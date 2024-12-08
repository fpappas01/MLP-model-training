import java.util.ArrayList;

public class Group {
    private Point centroid;
    private ArrayList<Point> members;
    private double groupError;
   
    public Group(Point centroid, ArrayList<Point> members, double groupError) {
        this.centroid = centroid;
        this.members = members;
        this.groupError = groupError;
    }

    public Point getCentroid() {
        return centroid;
    }
    public void setCentroid(Point centroid) {
        this.centroid = centroid;
    }
    public ArrayList<Point> getMembers() {
        return members;
    }

    public void addMember(Point member) {
        members.add(member);
    }
    public void setMembers(ArrayList<Point> members) {
        this.members = members;
    }
    public double getGroupError() {
        return groupError;
    }
    public void setGroupError(double groupError) {
        this.groupError = groupError;
    }
    
}
