package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Language {

    public Language(String language) {
        this.language = language;
    }

    public Language() {}

    @Id
    @GeneratedValue(generator = "increment")
    @Column(unique = true)
    private int languageId;

    private String language;

    @OneToMany(mappedBy = "language", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmployeeLanguage> languages = new ArrayList<>();

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<EmployeeLanguage> getLanguages() {
        return languages;
    }

    public void setLanguages(List<EmployeeLanguage> languages) {
        this.languages = languages;
    }
}
