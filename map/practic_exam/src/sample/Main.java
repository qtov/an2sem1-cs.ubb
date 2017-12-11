package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Test");
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        List<Articol> art = new ArrayList<>();
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

        Comp gen = new Comp();
        String aut = ".*autor5.*";
        Predicate<Articol> pred = x -> x.getAutori().matches(aut);
        Filter<Articol> f1 = new Filter<>(art, pred, gen);
//        for (Articol x : f1.doFilter()) {
//            System.out.println(x);
//        }

        String title = ".*titlu2.*";
        Predicate<Articol> pred3 = x -> x.getTitlu().matches(title);
        Filter<Articol> f3 = new Filter<>(art, pred3, gen);
//        for (Articol x : f3.doFilter()) {
//            System.out.println(x);
//        }

        String key = ".*qwe.*";
        Predicate<Articol> pred2 = x -> x.getKeywords().matches(key);
//        Comparator<Articol> comp2 = (x, y) -> x.getCod().compareTo(y.getCod());
        Filter<Articol> f2 = new Filter<>(art, pred, gen);
        for (Articol x : f2.doFilter()) {
            System.out.println(x);
        }

        launch(args);
    }
}
