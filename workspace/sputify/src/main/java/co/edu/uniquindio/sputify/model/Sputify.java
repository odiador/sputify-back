package co.edu.uniquindio.sputify.model;

import co.edu.uniquindio.sputify.structures.maps.HashMap;
import co.edu.uniquindio.sputify.structures.maps.Map;
import co.edu.uniquindio.sputify.structures.trees.BinaryTree;
import co.edu.uniquindio.sputify.structures.trees.Tree;
import lombok.Data;
import lombok.ToString;

@Data
public class Sputify {
	private Tree<Artist> lstArtists;
    private Map<String, User> lstUsers;

    public Sputify() {
        this.lstArtists = new BinaryTree<>();
        this.lstUsers = new HashMap<>();
    }
}
