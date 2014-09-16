package com.hodavidhara.elasticsearch;

/**
 *
 */
public enum Type {

    INDEXABLE("indexable"),
    DIRECTORY("directory"),
    DOCUMENT("document");

    private String typeName;

    Type(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public static Type getType(String typeName) {
        for (Type type : Type.values()) {
            if (type.getTypeName().equals(typeName)) {
                return type;
            }
        }
        return null;
    }
}
