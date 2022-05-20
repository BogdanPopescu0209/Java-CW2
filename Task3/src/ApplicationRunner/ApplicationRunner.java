package ApplicationRunner;

import javafx.scene.shape.Rectangle;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.Timer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;

public class ApplicationRunner extends Application {

    private int seconds = 0;
    private int minutes = 0;
    private int hours = 0;
    private String hour, minute, second;

    private double temp = 10.0;
    private double minTemp = 10.0;
    private double maxTemp = 27.5;
    private String resultTemp;

    private int humidity = 30;
    private int minHumidity = 30;
    private int maxHumidity = 55;
    private String resultHumidity;

    private int pressure = 50;
    private int minPressure = 50;
    private int maxPressure = 120;
    private String resultPressure;

    private int result1 = 0;
    private int result2 = 0;
    private int result3 = 0;

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {

        FlowPane flowPane = new FlowPane();

        //FlowPane spacing between nodes
        flowPane.setHgap(15);
        flowPane.setVgap(15);
        flowPane.setPadding(new Insets(30, 10, 5, 15));
        
        //Get nodes
        flowPane.getChildren().add(getDayTimeClock());
        flowPane.getChildren().add(getElapsedTime());
        flowPane.getChildren().add(getTemperatureControl());
        flowPane.getChildren().add(getHumidityControl());
        flowPane.getChildren().add(getPressureControl());
        flowPane.getChildren().add(getLightControl());
        flowPane.getChildren().add(getMedicalGases());

        //Set scene
        Scene scene = new Scene(flowPane, 750, 500);

        //FlowPane set stage and set background color
        flowPane.setStyle("-fx-background-color: SADDLEBROWN;");
        primaryStage.setResizable(false);
        primaryStage.setTitle("Surgery Control Panel");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    Pane getDayTimeClock() {

        StackPane stackPane = new StackPane();
        GridPane gridPane = new GridPane();

        Label DayTimeClock = new Label("Day Time Clock");

        DayTimeClock.setStyle("-fx-text-fill: white;" + "-fx-background-color: SADDLEBROWN;");
        StackPane.setAlignment(DayTimeClock, Pos.TOP_LEFT);
        
        //GridPane style
        gridPane.setStyle("-fx-border-color: white;");
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(30, 10, 5, 20));
        
        //GridPane dimensions
        double width = 305;
        double height = 130;
        gridPane.setMinWidth(width);
        gridPane.setMaxWidth(width);
        gridPane.prefWidth(width);
        gridPane.setMinHeight(height);
        gridPane.setMaxHeight(height);
        gridPane.prefHeight(height);

        Text clock = new Text();

        //Set function to display time
        Thread time = new Thread() {

            @Override
            public void run() {

                while (true) {

                    LocalDateTime myDateObj = LocalDateTime.now();
                    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern(" HH:mm:ss ");
                    String formattedDate = myDateObj.format(myFormatObj);

                    clock.setText(formattedDate);
                }
            }
        };

        time.start();

        clock.setStyle("-fx-font-size: 60;" + "-fx-fill: darkgreen;");

        gridPane.add(clock, 0, 0);
        stackPane.getChildren().addAll(gridPane, DayTimeClock);

        StackPane.setAlignment(gridPane, Pos.TOP_LEFT);

        return stackPane;  
    }

    Pane getElapsedTime() {

        StackPane stackPane = new StackPane();
        GridPane gridPane = new GridPane();
        
        //Style border
        gridPane.setStyle("-fx-border-color: white;");
        
        //Add label and set style
        Label ElapsedTime = new Label("Elapsed Time");
        ElapsedTime.setStyle("-fx-text-fill: white;" + "-fx-background-color: SADDLEBROWN;");
        StackPane.setAlignment(ElapsedTime, Pos.TOP_LEFT);
        
        //GridPane dimensions
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(15, 10, 5, 20));
        double width = 380;
        double height = 130;
        gridPane.setMinWidth(width);
        gridPane.setMaxWidth(width);
        gridPane.prefWidth(width);
        gridPane.setMinHeight(height);
        gridPane.setMaxHeight(height);
        gridPane.prefHeight(height);

        //Add buttons
        CustomButton start = new CustomButton("START", 70, 30);
        CustomButton stop = new CustomButton("STOP", 70, 30);
        CustomButton reset = new CustomButton("RESET", 70, 30);

        //Set style
        start.setStyle("-fx-background-color: blue;" + "-fx-border-color: white;" + "-fx-text-fill: white;" + "-fx-border-radius: 2;");
        stop.setStyle("-fx-background-color: blue;" + "-fx-border-color: white;" + "-fx-text-fill: white;" + "-fx-border-radius: 2;");
        reset.setStyle("-fx-background-color: blue;" + "-fx-border-color: white;" + "-fx-text-fill: white;" + "-fx-border-radius: 2;");

        VBox vBox = new VBox(5);
        vBox.setPadding(new Insets(1, 5, 5, 5));

        vBox.getChildren().add(start);
        vBox.getChildren().add(stop);
        vBox.getChildren().add(reset);

        Text clock = new Text();

        clock.setText("00:00:00");

        clock.wrappingWidthProperty().setValue(245);

        clock.setStyle("-fx-font-size: 60;" + "-fx-fill: red;");

        //Set function to output timer
        Timer timer = new Timer(1000, new ActionListener() {

            DecimalFormat format = new DecimalFormat("00");

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {

                seconds++;
                hour = format.format(hours);
                minute = format.format(minutes);
                second = format.format(seconds);
                clock.setText(hour + ":" + minute + ":" + second);

                if (seconds == 60) {

                    seconds = 0;
                    minutes++;
                    hour = format.format(hours);
                    minute = format.format(minutes);
                    second = format.format(seconds);
                    clock.setText(hour + ":" + minute + ":" + second);
                }

                if (minutes == 60) {

                    minutes = 0;
                    hours++;
                    hour = format.format(hours);
                    minute = format.format(minutes);
                    second = format.format(seconds);
                    clock.setText(hour + ":" + minute + ":" + second);
                }
            }

        });

        EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                timer.start();

            }
        };

        EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                timer.stop();

            }
        };

        EventHandler<ActionEvent> event3 = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                seconds = 0;
                minutes = 0;
                hours = 0;
                clock.setText("00:00:00");

            }
        };

        start.setOnAction(event1);
        stop.setOnAction(event2);
        reset.setOnAction(event3);

        gridPane.add(clock, 0, 0);
        gridPane.add(vBox, 3, 0);
        stackPane.getChildren().addAll(gridPane, ElapsedTime);

        StackPane.setAlignment(gridPane, Pos.TOP_RIGHT);

        return stackPane;
    }

    Pane getTemperatureControl() throws FileNotFoundException {

        StackPane stackPane = new StackPane();
        GridPane gridPane = new GridPane();

        //Add label and set style
        Label TemperatureControl = new Label("Temperature Control");
        TemperatureControl.setStyle("-fx-text-fill: white;" + "-fx-background-color: SADDLEBROWN;");
        StackPane.setAlignment(TemperatureControl, Pos.TOP_LEFT);
        
        //GridPane set style
        gridPane.setStyle("-fx-border-color: white;");
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(30, 10, 5, 20));
        double width = 224;
        double heigth = 140;
        gridPane.setMinWidth(width);
        gridPane.setMaxWidth(width);
        gridPane.prefWidth(width);
        gridPane.setMinHeight(heigth);
        gridPane.setMaxHeight(heigth);
        gridPane.prefHeight(heigth);

        //Add image
        String imgPath1 = System.getProperty("user.dir") + File.separator + "images" + File.separator + "up-icon.png";
        Image image1 = new Image(new FileInputStream(imgPath1));
        ImageView imageView1 = new ImageView(image1);

        //Add buttons
        CustomButton up = new CustomButton("", 30, 30);
        CustomButton down = new CustomButton("", 30, 30);

        //Set image dimensions
        up.setGraphic(imageView1);
        imageView1.setFitHeight(30);
        imageView1.setFitWidth(30);

        //Add image
        String imgPath2 = System.getProperty("user.dir") + File.separator + "images" + File.separator + "down-icon.png";
        Image image2 = new Image(new FileInputStream(imgPath2));
        ImageView imageView2 = new ImageView(image2);

        //Set image dimensions
        down.setGraphic(imageView2);
        imageView2.setFitHeight(30);
        imageView2.setFitWidth(30);

        HBox hBox = new HBox(5);
        hBox.setPadding(new Insets(5, 5, 5, 5));
        
        Text temperature = new Text();
        temperature.setWrappingWidth(80);
        Text degree = new Text();

        //Set fomrat
        DecimalFormat format = new DecimalFormat("00.0");

        //Fomrat temperature
        resultTemp = format.format(temp);

        //Output temperature
        degree.setText("°C");
        temperature.setText(resultTemp);

        //Set temperature style
        temperature.setStyle("-fx-font-size: 40;" + "-fx-fill: red;");
        degree.setStyle("-fx-font-size: 20;" + "-fx-fill: white;");

        hBox.getChildren().add(up);
        hBox.getChildren().add(down);

        //Add image
        String imgPath = System.getProperty("user.dir") + File.separator + "images" + File.separator + "thermometer-icon.png";
        Image image = new Image(new FileInputStream(imgPath));
        ImageView imageView = new ImageView(image);

        //Set image dimensions
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);

        EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                //If button pressed and temperature <maxTemp increase temperature
                if (temp <= maxTemp) {

                    temp = 0.1 + temp;
                    resultTemp = format.format(temp);
                    temperature.setText(resultTemp);

                }
            }
        };

        EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                //If button pressed and temperature >minTemo decrease temperature
                if (temp > minTemp) {

                    temp = temp - 0.1;
                    resultTemp = format.format(temp);
                    temperature.setText(resultTemp);

                }
            }
        };

        up.setOnAction(event1);
        down.setOnAction(event2);

        gridPane.add(imageView, 0, 0);
        gridPane.add(temperature, 1, 0);
        gridPane.add(degree, 2, 0);
        gridPane.add(hBox, 1, 1);

        stackPane.getChildren().addAll(gridPane, TemperatureControl);

        StackPane.setAlignment(gridPane, Pos.CENTER_LEFT);

        return stackPane;
    }

    Pane getHumidityControl() throws FileNotFoundException {

        StackPane stackPane = new StackPane();
        GridPane gridPane = new GridPane();
        
        //Add label and set style
        Label HumidityControl = new Label("Humidity Control");
        HumidityControl.setStyle("-fx-text-fill: white;" + "-fx-background-color: SADDLEBROWN;");
        StackPane.setAlignment(HumidityControl, Pos.TOP_LEFT);
        
        //GridPane set style
        gridPane.setStyle("-fx-border-color: white;");
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(30, 10, 5, 20));
        double width = 224;
        double heigth = 140;
        gridPane.setMinWidth(width);
        gridPane.setMaxWidth(width);
        gridPane.prefWidth(width);
        gridPane.setMinHeight(heigth);
        gridPane.setMaxHeight(heigth);
        gridPane.prefHeight(heigth);

        //Add image
        String imgPath1 = System.getProperty("user.dir") + File.separator + "images" + File.separator + "up-icon.png";
        Image image1 = new Image(new FileInputStream(imgPath1));
        ImageView imageView1 = new ImageView(image1);

        //Add buttons
        CustomButton up = new CustomButton("", 30, 30);
        CustomButton down = new CustomButton("", 30, 30);

        //Set image style
        up.setGraphic(imageView1);
        imageView1.setFitHeight(30);
        imageView1.setFitWidth(30);

        //Add image
        String imgPath2 = System.getProperty("user.dir") + File.separator + "images" + File.separator + "down-icon.png";
        Image image2 = new Image(new FileInputStream(imgPath2));
        ImageView imageView2 = new ImageView(image2);

        //Set image style
        down.setGraphic(imageView2);
        imageView2.setFitHeight(30);
        imageView2.setFitWidth(30);

        HBox hBox = new HBox(5);
        hBox.setPadding(new Insets(5, 5, 5, 5));

        Text humidityText = new Text();
        Text percent = new Text();

        //Set fomrat
        DecimalFormat format = new DecimalFormat("00");

        //Fomrat humidity
        resultHumidity = format.format(humidity);
        
        //Output humidity
        percent.setText("%");
        humidityText.setText(" " + resultHumidity);

        //Set style
        humidityText.setStyle("-fx-font-size: 40;" + "-fx-fill: red;");
        percent.setStyle("-fx-font-size: 20;" + "-fx-fill: white;");

        hBox.getChildren().add(up);
        hBox.getChildren().add(down);

        //Add image
        String imgPath = System.getProperty("user.dir") + File.separator + "images" + File.separator + "humidity-icon.png";
        Image image = new Image(new FileInputStream(imgPath));
        ImageView imageView = new ImageView(image);

        //Set image dimensions
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);

        EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                
                //If button pressed and humidity <maxHumidity increase humidity       
                if (humidity < maxHumidity) {

                    humidity = 1 + humidity;
                    resultHumidity = format.format(humidity);
                    humidityText.setText(" " + resultHumidity);

                }
            }
        };

        EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                //If button pressed and humidity >minHumidity decrease humidity 
                if (humidity > minHumidity) {

                    humidity = humidity - 1;
                    resultHumidity = format.format(humidity);
                    humidityText.setText(" " + resultHumidity);

                }

            }
        };

        up.setOnAction(event1);
        down.setOnAction(event2);

        gridPane.add(imageView, 0, 0);
        gridPane.add(humidityText, 1, 0);
        gridPane.add(percent, 2, 0);
        gridPane.add(hBox, 1, 1);

        stackPane.getChildren().addAll(gridPane, HumidityControl);

        StackPane.setAlignment(gridPane, Pos.CENTER);

        return stackPane;

    }

    Pane getPressureControl() throws FileNotFoundException {

        StackPane stackPane = new StackPane();
        GridPane gridPane = new GridPane();

        //Add label and set style
        Label PressureControl = new Label("Pressure Control");
        PressureControl.setStyle("-fx-text-fill: white;" + "-fx-background-color: SADDLEBROWN;");
        StackPane.setAlignment(PressureControl, Pos.TOP_LEFT);
        
        //GridPane dimensions
        gridPane.setStyle("-fx-border-color: white;");
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(30, 10, 5, 20));
        double x = 224;
        double y = 140;
        gridPane.setMinWidth(x);
        gridPane.setMaxWidth(x);
        gridPane.prefWidth(x);
        gridPane.setMinHeight(y);
        gridPane.setMaxHeight(y);
        gridPane.prefHeight(y);

        //Add image
        String imgPath1 = System.getProperty("user.dir") + File.separator + "images" + File.separator + "up-icon.png";
        Image image1 = new Image(new FileInputStream(imgPath1));
        ImageView imageView1 = new ImageView(image1);

        //Add button
        CustomButton up = new CustomButton("", 30, 30);
        CustomButton down = new CustomButton("", 30, 30);

        //Set image dimensions
        up.setGraphic(imageView1);
        imageView1.setFitHeight(30);
        imageView1.setFitWidth(30);

        //Add image
        String imgPath2 = System.getProperty("user.dir") + File.separator + "images" + File.separator + "down-icon.png";
        Image image2 = new Image(new FileInputStream(imgPath2));
        ImageView imageView2 = new ImageView(image2);

        //Set image dimensions
        down.setGraphic(imageView2);
        imageView2.setFitHeight(30);
        imageView2.setFitWidth(30);

        HBox hBox = new HBox(5);
        hBox.setPadding(new Insets(5, 5, 5, 5));
     
        Text pressureText = new Text();
        Text kiloPascal = new Text();

        //Set format
        DecimalFormat format = new DecimalFormat("00");

        //Format humidity
        resultPressure = format.format(pressure);

        //Output result
        kiloPascal.setText("kPa");
        pressureText.setText(" " + resultPressure);

        //Set style for output result
        pressureText.setStyle("-fx-font-size: 40;" + "-fx-fill: red;");
        kiloPascal.setStyle("-fx-font-size: 20;" + "-fx-fill: white;");

        hBox.getChildren().add(up);
        hBox.getChildren().add(down);

        //Add image
        String imgPath = System.getProperty("user.dir") + File.separator + "images" + File.separator + "pressure-icon.png";
        Image image = new Image(new FileInputStream(imgPath));
        ImageView imageView = new ImageView(image);

        //Set image dimensions
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);

        EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                //If button is pressed and pressure is <maxPressure increase pressure
                if (pressure < maxPressure) {

                    pressure = 10 + pressure;
                    resultPressure = format.format(pressure);
                    pressureText.setText(" " + resultPressure);

                }
            }
        };

        EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                //If button is pressed and pressure is >minPressure decrease pressure
                if (pressure > minPressure) {

                    pressure = pressure - 10;
                    resultPressure = format.format(pressure);
                    pressureText.setText(" " + resultPressure);

                }
            }
        };

        up.setOnAction(event1);
        down.setOnAction(event2);

        gridPane.add(imageView, 0, 0);
        gridPane.add(pressureText, 1, 0);
        gridPane.add(kiloPascal, 2, 0);
        gridPane.add(hBox, 1, 1);

        stackPane.getChildren().addAll(gridPane, PressureControl);

        StackPane.setAlignment(gridPane, Pos.CENTER_RIGHT);

        return stackPane;

    }

    Pane getLightControl() {

        StackPane stackPane = new StackPane();
        GridPane gridPane = new GridPane();

        //Set label and add style
        Label LightControl = new Label("Light Control");
        LightControl.setStyle("-fx-text-fill: white;" + "-fx-background-color: SADDLEBROWN;");
        StackPane.setAlignment(LightControl, Pos.TOP_LEFT);
        
        //GridPane dimensions
        gridPane.setStyle("-fx-border-color: white;");
        gridPane.setHgap(1);
        gridPane.setVgap(1);
        gridPane.setPadding(new Insets(30, 10, 5, 10));
        double x = 380;
        double y = 160;
        gridPane.setMinWidth(x);
        gridPane.setMaxWidth(x);
        gridPane.prefWidth(x);
        gridPane.setMinHeight(y);
        gridPane.setMaxHeight(y);
        gridPane.prefHeight(y);

        //Add buttons
        CustomButton minus1Btn = new CustomButton("-", 40, 30);
        CustomButton minus2Btn = new CustomButton("-", 40, 30);
        CustomButton minus3Btn = new CustomButton("-", 40, 30);
        //Set buttons style
        minus1Btn.setStyle("-fx-text-fill: white;" + "-fx-background-color: SADDLEBROWN;" + "-fx-border-color: white;" + "-fx-border-radius: 5;");
        minus2Btn.setStyle("-fx-text-fill: white;" + "-fx-background-color: SADDLEBROWN;" + "-fx-border-color: white;" + "-fx-border-radius: 5;");
        minus3Btn.setStyle("-fx-text-fill: white;" + "-fx-background-color: SADDLEBROWN;" + "-fx-border-color: white;" + "-fx-border-radius: 5;");

        //Add buttons
        CustomButton plus1Btn = new CustomButton("+", 40, 30);
        CustomButton plus2Btn = new CustomButton("+", 40, 30);
        CustomButton plus3Btn = new CustomButton("+", 40, 30);
        //Set buttons style
        plus1Btn.setStyle("-fx-text-fill: white;" + "-fx-background-color: SADDLEBROWN;" + "-fx-border-color: white;" + "-fx-border-radius: 5;");
        plus2Btn.setStyle("-fx-text-fill: white;" + "-fx-background-color: SADDLEBROWN;" + "-fx-border-color: white;" + "-fx-border-radius: 5;");
        plus3Btn.setStyle("-fx-text-fill: white;" + "-fx-background-color: SADDLEBROWN;" + "-fx-border-color: white;" + "-fx-border-radius: 5;");

        //Add rectangles and set dimensions
        int rectangleWidth = 40;
        int rectangleHeigth = 20;

        Rectangle rectangle1 = new Rectangle();
        rectangle1.setWidth(rectangleWidth);
        rectangle1.setHeight(rectangleHeigth);

        Rectangle rectangle2 = new Rectangle();
        rectangle2.setWidth(rectangleWidth);
        rectangle2.setHeight(rectangleHeigth);

        Rectangle rectangle3 = new Rectangle();
        rectangle3.setWidth(rectangleWidth);
        rectangle3.setHeight(rectangleHeigth);

        Rectangle rectangle4 = new Rectangle();
        rectangle4.setWidth(rectangleWidth);
        rectangle4.setHeight(rectangleHeigth);

        Rectangle rectangle5 = new Rectangle();
        rectangle5.setWidth(rectangleWidth);
        rectangle5.setHeight(rectangleHeigth);

        Rectangle rectangle6 = new Rectangle();
        rectangle6.setWidth(rectangleWidth);
        rectangle6.setHeight(rectangleHeigth);

        Rectangle rectangle7 = new Rectangle();
        rectangle7.setWidth(rectangleWidth);
        rectangle7.setHeight(rectangleHeigth);

        Rectangle rectangle8 = new Rectangle();
        rectangle8.setWidth(rectangleWidth);
        rectangle8.setHeight(rectangleHeigth);

        Rectangle rectangle9 = new Rectangle();
        rectangle9.setWidth(rectangleWidth);
        rectangle9.setHeight(rectangleHeigth);

        Rectangle rectangle10 = new Rectangle();
        rectangle10.setWidth(rectangleWidth);
        rectangle10.setHeight(rectangleHeigth);

        Rectangle rectangle11 = new Rectangle();
        rectangle11.setWidth(rectangleWidth);
        rectangle11.setHeight(rectangleHeigth);

        Rectangle rectangle12 = new Rectangle();
        rectangle12.setWidth(rectangleWidth);
        rectangle12.setHeight(rectangleHeigth);

        Rectangle rectangle13 = new Rectangle();
        rectangle13.setWidth(rectangleWidth);
        rectangle13.setHeight(rectangleHeigth);

        Rectangle rectangle14 = new Rectangle();
        rectangle14.setWidth(rectangleWidth);
        rectangle14.setHeight(rectangleHeigth);

        Rectangle rectangle15 = new Rectangle();
        rectangle15.setWidth(rectangleWidth);
        rectangle15.setHeight(rectangleHeigth);

        Rectangle rectangle16 = new Rectangle();
        rectangle16.setWidth(rectangleWidth);
        rectangle16.setHeight(rectangleHeigth);

        Rectangle rectangle17 = new Rectangle();
        rectangle17.setWidth(rectangleWidth);
        rectangle17.setHeight(rectangleHeigth);

        Rectangle rectangle18 = new Rectangle();
        rectangle18.setWidth(rectangleWidth);
        rectangle18.setHeight(rectangleHeigth);

        HBox hBox1 = new HBox(5);
        hBox1.setPadding(new Insets(1, 1, 1, 1));
        hBox1.setAlignment(Pos.CENTER);
        hBox1.getChildren().add(minus1Btn);
        hBox1.getChildren().add(rectangle1);
        hBox1.getChildren().add(rectangle2);
        hBox1.getChildren().add(rectangle3);
        hBox1.getChildren().add(rectangle4);
        hBox1.getChildren().add(rectangle5);
        hBox1.getChildren().add(rectangle6);
        hBox1.getChildren().add(plus1Btn);

        HBox hBox2 = new HBox(5);
        hBox2.setPadding(new Insets(1, 1, 1, 1));
        hBox2.setAlignment(Pos.CENTER);
        hBox2.getChildren().add(minus2Btn);
        hBox2.getChildren().add(rectangle7);
        hBox2.getChildren().add(rectangle8);
        hBox2.getChildren().add(rectangle9);
        hBox2.getChildren().add(rectangle10);
        hBox2.getChildren().add(rectangle11);
        hBox2.getChildren().add(rectangle12);
        hBox2.getChildren().add(plus2Btn);

        HBox hBox3 = new HBox(5);
        hBox3.setPadding(new Insets(1, 1, 1, 1));
        hBox3.setAlignment(Pos.CENTER);
        hBox3.getChildren().add(minus3Btn);
        hBox3.getChildren().add(rectangle13);
        hBox3.getChildren().add(rectangle14);
        hBox3.getChildren().add(rectangle15);
        hBox3.getChildren().add(rectangle16);
        hBox3.getChildren().add(rectangle17);
        hBox3.getChildren().add(rectangle18);
        hBox3.getChildren().add(plus3Btn);

        EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                //If button "-" check stage and change state
                result1 = result1 - 1;
                if (result1 == 0) {
                    rectangle1.setFill(Color.BLACK);
                    rectangle2.setFill(Color.BLACK);
                    rectangle3.setFill(Color.BLACK);
                    rectangle4.setFill(Color.BLACK);
                    rectangle5.setFill(Color.BLACK);
                    rectangle6.setFill(Color.BLACK);
                }
                if (result1 == 1) {
                    rectangle1.setFill(Color.YELLOW.darker().darker().darker());
                    rectangle2.setFill(Color.BLACK);
                    rectangle3.setFill(Color.BLACK.darker());
                    rectangle4.setFill(Color.BLACK);
                    rectangle5.setFill(Color.BLACK);
                    rectangle6.setFill(Color.BLACK);
                }
                if (result1 == 2) {
                    rectangle1.setFill(Color.YELLOW.darker().darker().darker());
                    rectangle2.setFill(Color.YELLOW.darker().darker());
                    rectangle3.setFill(Color.BLACK);
                    rectangle4.setFill(Color.BLACK);
                    rectangle5.setFill(Color.BLACK);
                    rectangle6.setFill(Color.BLACK);
                }
                if (result1 == 3) {
                    rectangle1.setFill(Color.YELLOW.darker().darker().darker());
                    rectangle2.setFill(Color.YELLOW.darker().darker());
                    rectangle3.setFill(Color.YELLOW.darker());
                    rectangle4.setFill(Color.BLACK);
                    rectangle5.setFill(Color.BLACK);
                    rectangle6.setFill(Color.BLACK);
                }
                if (result1 == 4) {
                    rectangle1.setFill(Color.YELLOW.darker().darker().darker());
                    rectangle2.setFill(Color.YELLOW.darker().darker());
                    rectangle3.setFill(Color.YELLOW.darker());
                    rectangle4.setFill(Color.YELLOW);
                    rectangle5.setFill(Color.BLACK);
                    rectangle6.setFill(Color.BLACK);
                }
                if (result1 == 5) {
                    rectangle1.setFill(Color.YELLOW.darker().darker().darker());
                    rectangle2.setFill(Color.YELLOW.darker().darker());
                    rectangle3.setFill(Color.YELLOW.darker());
                    rectangle4.setFill(Color.YELLOW);
                    rectangle5.setFill(Color.YELLOW.desaturate().desaturate().desaturate().desaturate());
                    rectangle6.setFill(Color.BLACK);
                }
            }
        };

        EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                //If button "-" check stage and change state
                result2 = result2 - 1;
                if (result2 == 0) {
                    rectangle7.setFill(Color.BLACK);
                    rectangle8.setFill(Color.BLACK);
                    rectangle9.setFill(Color.BLACK);
                    rectangle10.setFill(Color.BLACK);
                    rectangle11.setFill(Color.BLACK);
                    rectangle12.setFill(Color.BLACK);
                }
                if (result2 == 1) {
                    rectangle7.setFill(Color.YELLOW.darker().darker().darker());
                    rectangle8.setFill(Color.BLACK);
                    rectangle9.setFill(Color.BLACK.darker());
                    rectangle10.setFill(Color.BLACK);
                    rectangle11.setFill(Color.BLACK);
                    rectangle12.setFill(Color.BLACK);
                }
                if (result2 == 2) {
                    rectangle7.setFill(Color.YELLOW.darker().darker().darker());
                    rectangle8.setFill(Color.YELLOW.darker().darker());
                    rectangle9.setFill(Color.BLACK);
                    rectangle10.setFill(Color.BLACK);
                    rectangle11.setFill(Color.BLACK);
                    rectangle12.setFill(Color.BLACK);
                }
                if (result2 == 3) {
                    rectangle7.setFill(Color.YELLOW.darker().darker().darker());
                    rectangle8.setFill(Color.YELLOW.darker().darker());
                    rectangle9.setFill(Color.YELLOW.darker());
                    rectangle10.setFill(Color.BLACK);
                    rectangle11.setFill(Color.BLACK);
                    rectangle12.setFill(Color.BLACK);
                }
                if (result2 == 4) {
                    rectangle7.setFill(Color.YELLOW.darker().darker().darker());
                    rectangle8.setFill(Color.YELLOW.darker().darker());
                    rectangle9.setFill(Color.YELLOW.darker());
                    rectangle10.setFill(Color.YELLOW);
                    rectangle11.setFill(Color.BLACK);
                    rectangle12.setFill(Color.BLACK);
                }
                if (result2 == 5) {
                    rectangle7.setFill(Color.YELLOW.darker().darker().darker());
                    rectangle8.setFill(Color.YELLOW.darker().darker());
                    rectangle9.setFill(Color.YELLOW.darker());
                    rectangle10.setFill(Color.YELLOW);
                    rectangle11.setFill(Color.YELLOW.desaturate().desaturate().desaturate().desaturate());
                    rectangle12.setFill(Color.BLACK);
                }
            }
        };

        EventHandler<ActionEvent> event3 = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                //If button "-" check stage and change state
                result3 = result3 - 1;
                if (result3 == 0) {
                    rectangle13.setFill(Color.BLACK);
                    rectangle14.setFill(Color.BLACK);
                    rectangle15.setFill(Color.BLACK);
                    rectangle16.setFill(Color.BLACK);
                    rectangle17.setFill(Color.BLACK);
                    rectangle18.setFill(Color.BLACK);
                }
                if (result3 == 1) {
                    rectangle13.setFill(Color.YELLOW.darker().darker().darker());
                    rectangle14.setFill(Color.BLACK);
                    rectangle15.setFill(Color.BLACK.darker());
                    rectangle16.setFill(Color.BLACK);
                    rectangle17.setFill(Color.BLACK);
                    rectangle18.setFill(Color.BLACK);
                }
                if (result3 == 2) {
                    rectangle13.setFill(Color.YELLOW.darker().darker().darker());
                    rectangle14.setFill(Color.YELLOW.darker().darker());
                    rectangle15.setFill(Color.BLACK);
                    rectangle16.setFill(Color.BLACK);
                    rectangle17.setFill(Color.BLACK);
                    rectangle18.setFill(Color.BLACK);
                }
                if (result3 == 3) {
                    rectangle13.setFill(Color.YELLOW.darker().darker().darker());
                    rectangle14.setFill(Color.YELLOW.darker().darker());
                    rectangle15.setFill(Color.YELLOW.darker());
                    rectangle16.setFill(Color.BLACK);
                    rectangle17.setFill(Color.BLACK);
                    rectangle18.setFill(Color.BLACK);
                }
                if (result3 == 4) {
                    rectangle13.setFill(Color.YELLOW.darker().darker().darker());
                    rectangle14.setFill(Color.YELLOW.darker().darker());
                    rectangle15.setFill(Color.YELLOW.darker());
                    rectangle16.setFill(Color.YELLOW);
                    rectangle17.setFill(Color.BLACK);
                    rectangle18.setFill(Color.BLACK);
                }
                if (result3 == 5) {
                    rectangle13.setFill(Color.YELLOW.darker().darker().darker());
                    rectangle14.setFill(Color.YELLOW.darker().darker());
                    rectangle15.setFill(Color.YELLOW.darker());
                    rectangle16.setFill(Color.YELLOW);
                    rectangle17.setFill(Color.YELLOW.desaturate().desaturate().desaturate().desaturate());
                    rectangle18.setFill(Color.BLACK);
                }
            }
        };

        EventHandler<ActionEvent> event4 = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                //If button "+" check stage and change state
                result1 = result1 + 1;
                if (result1 == 1) {
                    rectangle1.setFill(Color.YELLOW.darker().darker().darker());
                    rectangle2.setFill(Color.BLACK);
                    rectangle3.setFill(Color.BLACK);
                    rectangle4.setFill(Color.BLACK);
                    rectangle5.setFill(Color.BLACK);
                    rectangle6.setFill(Color.BLACK);
                }
                if (result1 == 2) {
                    rectangle1.setFill(Color.YELLOW.darker().darker().darker());
                    rectangle2.setFill(Color.YELLOW.darker().darker());
                    rectangle3.setFill(Color.BLACK);
                    rectangle4.setFill(Color.BLACK);
                    rectangle5.setFill(Color.BLACK);
                    rectangle6.setFill(Color.BLACK);
                }
                if (result1 == 3) {
                    rectangle1.setFill(Color.YELLOW.darker().darker().darker());
                    rectangle2.setFill(Color.YELLOW.darker().darker());
                    rectangle3.setFill(Color.YELLOW.darker());
                    rectangle4.setFill(Color.BLACK);
                    rectangle5.setFill(Color.BLACK);
                    rectangle6.setFill(Color.BLACK);
                }
                if (result1 == 4) {
                    rectangle1.setFill(Color.YELLOW.darker().darker().darker());
                    rectangle2.setFill(Color.YELLOW.darker().darker());
                    rectangle3.setFill(Color.YELLOW.darker());
                    rectangle4.setFill(Color.YELLOW);
                    rectangle5.setFill(Color.BLACK);
                    rectangle6.setFill(Color.BLACK);
                }
                if (result1 == 5) {
                    rectangle1.setFill(Color.YELLOW.darker().darker().darker());
                    rectangle2.setFill(Color.YELLOW.darker().darker());
                    rectangle3.setFill(Color.YELLOW.darker());
                    rectangle4.setFill(Color.YELLOW);
                    rectangle5.setFill(Color.YELLOW.brighter());
                    rectangle6.setFill(Color.BLACK);
                }
                if (result1 == 6) {
                    rectangle1.setFill(Color.YELLOW.darker().darker().darker());
                    rectangle2.setFill(Color.YELLOW.darker().darker());
                    rectangle3.setFill(Color.YELLOW.darker());
                    rectangle4.setFill(Color.YELLOW);
                    rectangle5.setFill(Color.YELLOW.desaturate().desaturate().desaturate().desaturate());
                    rectangle6.setFill(Color.YELLOW.desaturate().desaturate().desaturate().desaturate().desaturate());
                }
            }
        };

        EventHandler<ActionEvent> event5 = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                //If button "+" check stage and change state
                result2 = result2 + 1;
                if (result2 == 1) {
                    rectangle7.setFill(Color.YELLOW.darker().darker().darker());
                    rectangle8.setFill(Color.BLACK);
                    rectangle9.setFill(Color.BLACK);
                    rectangle10.setFill(Color.BLACK);
                    rectangle11.setFill(Color.BLACK);
                    rectangle12.setFill(Color.BLACK);
                }
                if (result2 == 2) {
                    rectangle7.setFill(Color.YELLOW.darker().darker().darker());
                    rectangle8.setFill(Color.YELLOW.darker().darker());
                    rectangle9.setFill(Color.BLACK);
                    rectangle10.setFill(Color.BLACK);
                    rectangle11.setFill(Color.BLACK);
                    rectangle12.setFill(Color.BLACK);
                }
                if (result2 == 3) {
                    rectangle7.setFill(Color.YELLOW.darker().darker().darker());
                    rectangle8.setFill(Color.YELLOW.darker().darker());
                    rectangle9.setFill(Color.YELLOW.darker());
                    rectangle10.setFill(Color.BLACK);
                    rectangle11.setFill(Color.BLACK);
                    rectangle12.setFill(Color.BLACK);
                }
                if (result2 == 4) {
                    rectangle7.setFill(Color.YELLOW.darker().darker().darker());
                    rectangle8.setFill(Color.YELLOW.darker().darker());
                    rectangle9.setFill(Color.YELLOW.darker());
                    rectangle10.setFill(Color.YELLOW);
                    rectangle11.setFill(Color.BLACK);
                    rectangle12.setFill(Color.BLACK);
                }
                if (result2 == 5) {
                    rectangle7.setFill(Color.YELLOW.darker().darker().darker());
                    rectangle8.setFill(Color.YELLOW.darker().darker());
                    rectangle9.setFill(Color.YELLOW.darker());
                    rectangle10.setFill(Color.YELLOW);
                    rectangle11.setFill(Color.YELLOW.brighter());
                    rectangle12.setFill(Color.BLACK);
                }
                if (result2 == 6) {
                    rectangle7.setFill(Color.YELLOW.darker().darker().darker());
                    rectangle8.setFill(Color.YELLOW.darker().darker());
                    rectangle9.setFill(Color.YELLOW.darker());
                    rectangle10.setFill(Color.YELLOW);
                    rectangle11.setFill(Color.YELLOW.desaturate().desaturate().desaturate());
                    rectangle12.setFill(Color.YELLOW.desaturate().desaturate().desaturate().desaturate().desaturate());
                }
            }
        };

        EventHandler<ActionEvent> event6 = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                //If button "+" check stage and change state
                result3 = result3 + 1;
                if (result3 == 1) {
                    rectangle13.setFill(Color.YELLOW.darker().darker().darker());
                    rectangle14.setFill(Color.BLACK);
                    rectangle15.setFill(Color.BLACK);
                    rectangle16.setFill(Color.BLACK);
                    rectangle17.setFill(Color.BLACK);
                    rectangle18.setFill(Color.BLACK);
                }
                if (result3 == 2) {
                    rectangle13.setFill(Color.YELLOW.darker().darker().darker());
                    rectangle14.setFill(Color.YELLOW.darker().darker());
                    rectangle15.setFill(Color.BLACK);
                    rectangle16.setFill(Color.BLACK);
                    rectangle17.setFill(Color.BLACK);
                    rectangle18.setFill(Color.BLACK);
                }
                if (result3 == 3) {
                    rectangle13.setFill(Color.YELLOW.darker().darker().darker());
                    rectangle14.setFill(Color.YELLOW.darker().darker());
                    rectangle15.setFill(Color.YELLOW.darker());
                    rectangle16.setFill(Color.BLACK);
                    rectangle17.setFill(Color.BLACK);
                    rectangle18.setFill(Color.BLACK);
                }
                if (result3 == 4) {
                    rectangle13.setFill(Color.YELLOW.darker().darker().darker());
                    rectangle14.setFill(Color.YELLOW.darker().darker());
                    rectangle15.setFill(Color.YELLOW.darker());
                    rectangle16.setFill(Color.YELLOW);
                    rectangle17.setFill(Color.BLACK);
                    rectangle18.setFill(Color.BLACK);
                }
                if (result3 == 5) {
                    rectangle13.setFill(Color.YELLOW.darker().darker().darker());
                    rectangle14.setFill(Color.YELLOW.darker().darker());
                    rectangle15.setFill(Color.YELLOW.darker());
                    rectangle16.setFill(Color.YELLOW);
                    rectangle17.setFill(Color.YELLOW.brighter());
                    rectangle18.setFill(Color.BLACK);
                }
                if (result3 == 6) {
                    rectangle13.setFill(Color.YELLOW.darker().darker().darker());
                    rectangle14.setFill(Color.YELLOW.darker().darker());
                    rectangle15.setFill(Color.YELLOW.darker());
                    rectangle16.setFill(Color.YELLOW);
                    rectangle17.setFill(Color.YELLOW.desaturate().desaturate().desaturate());
                    rectangle18.setFill(Color.YELLOW.desaturate().desaturate().desaturate().desaturate().desaturate());
                }
            }
        };

        minus1Btn.setOnAction(event1);
        minus2Btn.setOnAction(event2);
        minus3Btn.setOnAction(event3);
        plus1Btn.setOnAction(event4);
        plus2Btn.setOnAction(event5);
        plus3Btn.setOnAction(event6);

        gridPane.add(hBox1, 0, 0);
        gridPane.add(hBox2, 0, 1);
        gridPane.add(hBox3, 0, 2);

        stackPane.getChildren().addAll(gridPane, LightControl);

        StackPane.setAlignment(gridPane, Pos.BOTTOM_LEFT);

        return stackPane;

    }

    Pane getMedicalGases() {

        StackPane stackPane = new StackPane();
        GridPane gridPane = new GridPane();

        //Add label and set style
        Label MedicalGases = new Label("Medical Gases");
        MedicalGases.setStyle("-fx-text-fill: white;" + "-fx-background-color: SADDLEBROWN;");
        StackPane.setAlignment(MedicalGases, Pos.TOP_LEFT);
        
        //GridPane dimensions
        gridPane.setStyle("-fx-border-color: white;");
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(30, 10, 5, 20));
        double width = 310;
        double height = 160;
        gridPane.setMinWidth(width);
        gridPane.setMaxWidth(width);
        gridPane.prefWidth(width);
        gridPane.setMinHeight(height);
        gridPane.setMaxHeight(height);
        gridPane.prefHeight(height);
       
        //Add rectangles
        int rectangleWidth = 50;
        int rectangleHeight = 50;
        
        Rectangle rectangle1 = new Rectangle();
        rectangle1.setWidth(rectangleWidth);
        rectangle1.setHeight(rectangleHeight);
        rectangle1.setFill(Color.RED);
        
        Rectangle rectangle2 = new Rectangle();
        rectangle2.setWidth(rectangleWidth);
        rectangle2.setHeight(rectangleHeight);
        rectangle2.setFill(Color.RED);
        
        Rectangle rectangle3 = new Rectangle();
        rectangle3.setWidth(rectangleWidth);
        rectangle3.setHeight(rectangleHeight);
        rectangle3.setFill((Color.GREENYELLOW));
        
        Rectangle rectangle4 = new Rectangle();
        rectangle4.setWidth(rectangleWidth);
        rectangle4.setHeight(rectangleHeight);
        rectangle4.setFill(Color.GREENYELLOW);
        
        Rectangle rectangle5 = new Rectangle();
        rectangle5.setWidth(rectangleWidth);
        rectangle5.setHeight(rectangleHeight);
        rectangle5.setFill(Color.RED);
        
        //Add title
        Text text1 = new Text();
        text1.setText("O2");
        text1.setStyle("-fx-fill: yellow;");
        
        Text text2 = new Text();
        text2.setText("N20");
        text2.setStyle("-fx-fill: yellow;");
        
        Text text3 = new Text();
        text3.setText("AIR1");
        text3.setStyle("-fx-fill: yellow;");
        
        Text text4 = new Text();
        text4.setText("CO2");
        text4.setStyle("-fx-fill: yellow;");
        
        Text text5 = new Text();
        text5.setText("VAC");
        text5.setStyle("-fx-fill: yellow;");
        
        HBox hBox2 = new HBox(5);
        hBox2.setAlignment(Pos.CENTER);
        hBox2.setSpacing(30);
        hBox2.setPadding(new Insets(5, 1, 1, 1));
        hBox2.getChildren().add(text1);
        hBox2.getChildren().add(text2);
        hBox2.getChildren().add(text3);
        hBox2.getChildren().add(text4);
        hBox2.getChildren().add(text5);
        
        //Add gasses levels
        Text text6 = new Text();
        text6.setText("Low");
        StackPane.setAlignment(text6, Pos.CENTER);
        
        Text text7 = new Text();
        text7.setText("High");
        StackPane.setAlignment(text7, Pos.CENTER);
        
        Text text8 = new Text();
        text8.setText("Norm");
        StackPane.setAlignment(text8, Pos.CENTER);
        
        Text text9 = new Text();
        text9.setText("Norm");
        StackPane.setAlignment(text9, Pos.CENTER);
     
        Text text10 = new Text();
        text10.setText("High");
        StackPane.setAlignment(text10, Pos.CENTER);
            
        StackPane stack1 = new StackPane();
        stack1.getChildren().addAll(rectangle1, text6);
        
        StackPane stack2 = new StackPane();
        stack2.getChildren().addAll(rectangle2, text7);
        
        StackPane stack3 = new StackPane();
        stack3.getChildren().addAll(rectangle3, text8);
        
        StackPane stack4 = new StackPane();
        stack4.getChildren().addAll(rectangle4, text9);
        
        StackPane stack5 = new StackPane();
        stack5.getChildren().addAll(rectangle5, text10);
        
        HBox hBox1 = new HBox(5);
        hBox1.setPadding(new Insets(1, 1, 1, 1));
        hBox1.getChildren().add(stack1);
        hBox1.getChildren().add(stack2);
        hBox1.getChildren().add(stack3);
        hBox1.getChildren().add(stack4);
        hBox1.getChildren().add(stack5);
        
        gridPane.add(hBox2, 0, 0);
        gridPane.add(hBox1, 0, 1);
        stackPane.getChildren().addAll(gridPane, MedicalGases);
 
        
        StackPane.setAlignment(gridPane, Pos.BOTTOM_RIGHT);
        

        return stackPane;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
