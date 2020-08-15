package be.li.mymeal.entities;

import com.google.gson.annotations.SerializedName;

public class Ingredient {
    @SerializedName("idIngredient")
    public String id;

    @SerializedName("strIngredient")
    public String name;

    @SerializedName("strDescription")
    public String description;

    @SerializedName("strType")
    public String type;
}
