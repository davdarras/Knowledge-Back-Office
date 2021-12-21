package fr.insee.knowledge.domain;

import java.util.List;

public class Gsbpm {

    private String id;
    private String label;
    private String description;
    private List<Gsbpm> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Gsbpm> getChildren() {
        return children;
    }

    public void setChildren(List<Gsbpm> children) {
        this.children = children;
    }
}
