//package Gijs;
//
//import Config.config;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import javafx.geometry.HPos;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.layout.GridPane;
//import javafx.scene.paint.Color;
//import javafx.stage.Stage;
//
///**
// *
// * @author Gijs
// */
//public class PDialoog{
//    
//    public static void laatZien() {
//        Stage dialoog;
//        GridPane grid;
//        
//        dialoog = new Stage();
//        dialoog.setTitle("Pakket toevoegen");
//        dialoog.setMinHeight(200);
//        dialoog.setMinWidth(200);
//        grid = new GridPane();
//        grid.setVgap(7);
//        grid.setHgap(10);
//        
//        Label lengte = new Label("Lengte:");
//        Label breedte = new Label("Breedte:");
//        Label hoogte = new Label("Hoogte:");
//        Label gewicht = new Label("Gewicht");
//        Label idontvanger = new Label("Id ontvanger:");
//        Label idafzender = new Label("Id afzender:");
//        Label barcode = new Label("Barcode:");
//        Label locatie = new Label("Locatie:");
//       
//        Label foutLI = new Label("");
//        Label foutBI = new Label("");
//        Label foutHI = new Label("");
//        Label foutGI = new Label("");
//        Label foutIOI = new Label("");
//        Label foutIAI = new Label("");
//        Label foutBarI = new Label("");
//        Label foutLoI = new Label("");
//        
//        Label succesLabel = new Label("");
//        
//        
//        TFToevoegen lengteI = new TFToevoegen("Lengte");
//        TFToevoegen breedteI = new TFToevoegen("Breedte");
//        TFToevoegen hoogteI = new TFToevoegen("Hoogte");
//        TFToevoegen gewichtI = new TFToevoegen("Gewicht");
//        TFToevoegen idontvangerI = new TFToevoegen("Idontvanger");
//        TFToevoegen idafzenderI = new TFToevoegen("Idafzender");
//        TFToevoegen barcodeI = new TFToevoegen("Barcode");
//        TFToevoegen locatieI = new TFToevoegen("Locatie");
//        
//        
//        Button toevoegen = new Button("Toevoegen!");
//        
//        toevoegen.setOnAction((Event) -> { 
//            if(lengteI.getText().equals("")) {
//                foutLI.setText("Vul dit veld in");
//                foutLI.setTextFill(Color.RED);
//            }   
//            if(breedteI.getText().equals("")) {
//                foutBI.setText("Vul dit veld in");
//                foutBI.setTextFill(Color.RED);
//            }
//            if(hoogteI.getText().equals("")) {
//                foutHI.setText("Vul dit veld in");
//                foutHI.setTextFill(Color.RED);
//            }
//            if(gewichtI.getText().equals("")) {
//                foutGI.setText("Vul dit veld in");
//                foutGI.setTextFill(Color.RED);
//            }
//            if(idontvangerI.getText().equals("")) {
//                foutIOI.setText("Vul dit veld in");
//                foutIOI.setTextFill(Color.RED);
//            }
//            if(idafzenderI.getText().equals("")) {
//                foutIAI.setText("Vul dit veld in");
//                foutIAI.setTextFill(Color.RED);
//            }
//            if(barcodeI.getText().equals("")) {
//                foutBarI.setText("Vul dit veld in");
//                foutBarI.setTextFill(Color.RED);
//            }
//             if(locatieI.getText().equals("")) {
//                foutLoI.setText("Vul dit veld in");
//                foutLoI.setTextFill(Color.RED);
//            }
//            try{ 
//            int lengteQ = Integer.parseInt(lengteI.getText());
//            
//            int breedteQ = Integer.parseInt(breedteI.getText());
//            int hoogteQ = Integer.parseInt(hoogteI.getText());
//            int gewichtQ = Integer.parseInt(gewichtI.getText());
//            int idOntvangerQ = Integer.parseInt(idontvangerI.getText());
//            int idAfzenderQ = Integer.parseInt(idafzenderI.getText());
//            String barcodeQ = barcodeI.getText();
//            int locatieQ = Integer.parseInt(locatieI.getText());
//            
//             Connection conn;
//        
//            //MySQL driver aanroepen  
//            Class.forName(config.DRIVER).newInstance();
//            //Connectie wordt gemaakt
//            conn = DriverManager.getConnection(config.URL, config.USERNAME, config.PASSWORD);
//            //Query wordt uitgevoerd en in een ResultSet gezet
//            PreparedStatement st =  conn.prepareStatement("INSERT INTO pakket(lengte, breedte, hoogte, gewicht, ontvanger_idontvanger, afzender_idafzender, barcode, locatie, verwijderd) VALUES (?,?,?,?,?,?,?,?,?)");
//            st.setString(1, lengteQ+"");
//            st.setString(2, breedteQ+"");
//            st.setString(3, hoogteQ+"");
//            st.setString(4, gewichtQ+"");
//            st.setString(5, idOntvangerQ+"");
//            st.setString(6, idAfzenderQ+"");
//            st.setString(7, barcodeQ);
//            st.setString(8, locatieQ+"");
//            st.setString(9, 0+"");
//
//            st.executeUpdate();
//            st.close();
//            succesLabel.setText("Pakket toegevoegd!");
//            succesLabel.setTextFill(Color.GREEN);
//        }  catch (NumberFormatException | ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
//            System.err.println(ex.getMessage());
//            System.out.println(ex.getCause());
//        }
//        });
//        
//    
//        Button annuleren = new Button("Annuleren");
//        annuleren.setOnAction((Event) -> {
//            dialoog.close();
//        });
//        
//        grid.setHalignment(lengte, HPos.RIGHT);
//        grid.add(lengte, 0, 1);
//        grid.setHalignment(breedte, HPos.RIGHT);
//        grid.add(breedte, 0, 2);
//        grid.setHalignment(hoogte, HPos.RIGHT);
//        grid.add(hoogte, 0, 3);
//        grid.setHalignment(gewicht, HPos.RIGHT);
//        grid.add(gewicht, 0, 4);
//        grid.setHalignment(idontvanger, HPos.RIGHT);
//        grid.add(idontvanger, 0, 5);
//        grid.setHalignment(idafzender, HPos.RIGHT);
//        grid.add(idafzender, 0, 6);
//        grid.setHalignment(barcode, HPos.RIGHT);
//        grid.add(barcode, 0, 7);
//        grid.setHalignment(locatie, HPos.RIGHT);
//        
//        grid.add(foutLI, 2, 1);
//        grid.setHalignment(foutLI, HPos.RIGHT);
//        grid.add(foutBI, 2, 2);
//        grid.setHalignment(foutBI, HPos.RIGHT);
//        grid.add(foutHI, 2, 3);
//        grid.setHalignment(foutHI, HPos.RIGHT);
//        grid.add(foutGI, 2, 4);
//        grid.setHalignment(foutGI, HPos.RIGHT);
//        grid.add(foutIOI, 2, 5);
//        grid.setHalignment(foutIOI, HPos.RIGHT);
//        grid.add(foutIAI, 2, 6);
//        grid.setHalignment(foutIAI, HPos.RIGHT);
//        grid.add(foutBarI, 2, 7);
//        grid.setHalignment(foutBarI, HPos.RIGHT);
//        grid.add(foutLoI, 2, 8);
//        grid.setHalignment(foutLoI, HPos.RIGHT);
//        
//        grid.add(succesLabel,1, 9);
//                              
//        grid.add(locatie, 0, 8);
//        grid.add(lengteI, 1, 1);
//        grid.add(breedteI, 1, 2);
//        grid.add(hoogteI, 1, 3);
//        grid.add(gewichtI, 1, 4);
//        grid.add(idontvangerI, 1, 5);
//        grid.add(idafzenderI, 1, 6);
//        grid.add(barcodeI, 1, 7);
//        grid.add(locatieI, 1, 8);
//        
//        grid.setHalignment(toevoegen, HPos.RIGHT);
//        grid.add(toevoegen, 2, 9);
//        grid.setHalignment(annuleren, HPos.RIGHT);
//        grid.add(annuleren, 3, 9);
//        
//        
//        Scene scene = new Scene(grid);
//        dialoog.setScene(scene);
//        dialoog.showAndWait();
//        
//        
//        
//        
//        
//    }
//     
//}
