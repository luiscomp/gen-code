package ui.model;

public class ObjectTree<TypeClass> {
	private TypeClass object;
	private boolean selected;
	
	public ObjectTree() {
		super();
	}
	
	public ObjectTree(TypeClass object, boolean selected) {
		super();
		this.object = object;
		this.selected = selected;
	}

	public TypeClass getObject() { return object; }
	public void setObject(TypeClass object) { this.object = object; }

	public boolean isSelected() { return selected; }
	public void setSelected(boolean selected) { this.selected = selected; }
	
	@Override
    public String toString() {
        return object.toString();
    }
}
