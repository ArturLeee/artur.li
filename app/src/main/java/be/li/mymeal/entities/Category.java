package be.li.mymeal.entities;

import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName("idCategory")
    public String id;

    @SerializedName("strCategory")
    public String name;

    @SerializedName("strCategoryThumb")
    public String thumbnail;

    @SerializedName("strCategoryDescription")
    public String description;
}
