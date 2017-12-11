package sample;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import javax.swing.text.TableView;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class Controller {

    private List<Articol> art;
    private Filter<Articol> f1;
    private Filter<Articol> f2;
    private Filter<Articol> f3;

    @FXML private TableView table;
    @FXML private TextField key1;
    @FXML private TextField key2;
    @FXML private TextField key3;
    @FXML private TextField key4;
    @FXML private TextField tit1;
    @FXML private ComboBox<String> aut1;
    @FXML private Button keyb;
    @FXML private Button titb;
    @FXML private Button autb;

    private List<String> names;

    public Controller() {}

    @FXML
    public void initialize() {
        this.art = new ArrayList<>();
        art.add(new Articol("1", Domeniu.Mate, "titlu1", "autor1;autor2", "qwe;asd;wer"));
        art.add(new Articol("2", Domeniu.Fizica, "titlu2", "autor2;autor2", "qwe;asd;zxc"));
        art.add(new Articol("3", Domeniu.Mate, "titlu3", "autor3;autor2", "qwe;asd;zxc"));
        art.add(new Articol("4", Domeniu.Info, "titlu4", "autor4;autor2", "qwe;asd;zxc"));
        art.add(new Articol("5", Domeniu.Fizica, "titlu5", "autor5;autor3", "qwe;wer;zxc"));
        art.add(new Articol("6", Domeniu.Mate, "titlu6", "autor6;autor2", "qwe;asd;zxc"));
        art.add(new Articol("7", Domeniu.Info, "titlu7", "autor7;autor5", "qwe;wer;zxc"));
        art.add(new Articol("8", Domeniu.Fizica, "titlu8", "autor8;autor2", "qwe;asd;zxc"));
        art.add(new Articol("9", Domeniu.Mate, "titlu9", "autor9;autor2", "qwe;wer;zxc"));
        art.add(new Articol("10", Domeniu.Info, "titlu10", "autor5;autor2", "qwe;asd;zxc"));

        filters();

        this.names = new ArrayList<>();
        for (Articol x : art) {
            String[] aux = x.getAutori().split(";");
            for (int l = 0; l < aux.length; l++) {
                names.add(aux[l]);
            }
        }

        aut1.setItems(FXCollections.observableArrayList(names));
        aut1.getSelectionModel().selectFirst();

        tit1.setText("test");

        System.out.println("test");
    }

    public void filters() {
        Comp gen = new Comp();
        String aut = ".*autor5.*";
        Predicate<Articol> pred = x -> x.getAutori().matches(aut);
        f1 = new Filter<>(art, pred, gen);


        String title = ".*title.*";
        Predicate<Articol> pred3 = x -> x.getTitlu().matches(title);
        f3 = new Filter<>(art, pred3, gen);

        String key = ".*qwe.*";
        Predicate<Articol> pred2 = x -> x.getKeywords().matches(key);
//        Comparator<Articol> comp2 = (x, y) -> x.getCod().compareTo(y.getCod());
        f2 = new Filter<>(art, pred, gen);
    }

}
