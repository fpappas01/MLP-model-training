import java.util.ArrayList;

public class Group {
    private PointSDO centroid;
    private ArrayList<PointSDO> members;
    private double groupError;
   
    public Group(PointSDO centroid, ArrayList<PointSDO> members, double groupError) {
        this.centroid = centroid;
        this.members = members;
        this.groupError = groupError;
    }

    public PointSDO getCentroid() {
        return centroid;
    }
    public void setCentroid(PointSDO centroid) {
        this.centroid = centroid;
    }
    public ArrayList<PointSDO> getMembers() {
        return members;
    }

    public void addMember(PointSDO member) {
        members.add(member);
    }
    public void setMembers(ArrayList<PointSDO> members) {
        this.members = members;
    }
    public double getGroupError() {
        return groupError;
    }
    public void setGroupError(double groupError) {
        this.groupError = groupError;
    }
    
}
