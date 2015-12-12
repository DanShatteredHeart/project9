//project 9
//Danny Raymundo

float horizon;
int score;
float boatX=0, boatDX=5;
float waterR, waterG, waterB;
String [] squidNames = {"Callie", "Marie", "Judd", "Octavio", "Cuttlefiss"};
String [] boatNames = {"Dan", "Ralph", "Jose", "Herber"};
Squid [] school;
Boat [] fleet;
Sun light; 

void setup() {
  size( 800, 600 );
 horizon = height/4;
  reset();
}
void reset() {
  score = 0;
  light = new Sun();
  school = new Squid[5];
  float squidX = width/4;
  for (int i=0; i<school.length; i++ ) {
    school[i] = new Squid(squidX, squidNames[i]);
    squidX += 145;
  }
  fleet = new Boat[4];
  for (int i=0; i<fleet.length; i++) {
    fleet[i] = new Boat(boatNames[i]);
  }
}
void draw () {
  background(129, 255, 254);
  scene();
  messages();
  if (key >= 'A' && key <= 'S') {
    squidScore(school, school.length);
    boatScore(fleet, fleet.length);
  } else {
    
    action();
  }
}
void squidScore(Squid[] a, int m) {
  float x = width/4-100;
  float y = height/4-100;
  float w = width/4*1.7;
  float h = height/4*1.7;
  fill(255);
  rect(x-20, y-30, w, h);
  fill(0, 0, 0);
  textSize(15);
  text("Squid Score.", x+100, y);
  textSize(10);
  text("Name", x, y+20);
  text("Legs", x+75, y+20);
  text("X", x+140, y+20);
  text("Y", x+215, y+20);
  text("DY", x+275, y+20);
  for (int i=0; i<m; i++) {
    fill(0);
    text(a[i].name, x, y+40);
    text(a[i].legs, x+75, y+40);
    text(a[i].x, x+140, y+40);
    text(a[i].y, x+215, y+40);
    text(a[i].dy, x+275, y+40);
    y += 35;
  }
}

void boatScore(Boat[]a, int m) { 
  float x = width/2-100;
  float y = height/2+10;
  float w = width/2-50;
  float h = height/2-20;
  fill(100);
  rect(x-20, y-30, w, h);
  fill(255);
  textSize(15);
  text("Boat Score.", x+100, y);
  textSize(10);
  text("Name", x, y+20);
  text("Cargo", x+75, y+20);
  text("X", x+175, y+20);
  text("DX", x+275, y+20);
  for (int i=0; i<m; i++) {
    text(a[i].name, x, y+40);
    text(a[i].cargo, x+75, y+40);
    text(a[i].x, x+175, y+40);
    text(a[i].dx, x+275, y+40);
    y += 50;
  }
}

void action () {
  for (int i=0; i<school.length; i++) {
    school[i].move();
  }
  for (int i=0; i<fleet.length; i++) {
    fleet[i].move();
  }
}
void scene () {
  noStroke();
  fill(233, 154, 214);
  rect(0, horizon, width, height);
  stroke(0);
  
  for (int i=0; i<school.length; i++) {
    school[i].show();
  }
  for (int i=0; i<fleet.length; i++) {
    fleet[i].show();
  }
  light.show();
  light.move();
}
void messages() {
  textSize(13);
  fill(0);
  text("Danny Raymundo", width/2, horizon-120);
  text ("Score", width*3/4, horizon-100);
  text(score, width*3/4 +50, horizon - 100);
  text("Project 9", width*3/4, horizon - 120);
  text("Press 'r' to reset", width/4-180, horizon -85);
  textSize(13);
}

class Squid {
  float x, y, dx, dy;
  float r, g, b;
  int legs = 8;
  int count = 0;
  String name = "";
  
  Squid(float x, String name) {
    this.name = name;
    this.x = x;
    r = random(255);
    g = random(255);
    b = random(255);
    seaFloor();
  }
  void seaFloor() {
    y= height -20;
    dy = -random(0.2, 2);
    legs = int(random(1,6));
  }
  void show() {
    count++;
    noStroke();
    fill(r, g, b);
    ellipse(x-70, y, 40, 40);
    rect(x-90, y, 40, 20);
    fill(0);
    text(name, x-80, y);
    text(legs, x-75, y + 10);
    
    strokeWeight(3);
    stroke(random(255));
    float legX = x-18;
    for (int i=0; i<legs; i++) {
      if (count/30 % 2 == 0) {
        line(legX-70, y+20, legX-80, y+45);
      } else {
        line(legX-70, y+20, legX-60, y+45);
      }
      legX +=8;
      
      stroke(random(255));
      strokeWeight(4);
    }
  }
  
  void move() {
    y+= dy;
    if (y < horizon || y > height) dy *= -1;
  }
}

class Boat {
  float x, y, dx;
  float r, g, b;
  String name = "";
  int cargo=0, caught=0;

  Boat(String name) {
    this.name = name;
    x= random(30, width - 60);
    y = horizon - 25;
    dx = random(-6, 6);
    r = random(255);
    g = random(255);
    b = random(255);
  }
  
  void show() {
   noStroke(); 
    fill(r, g, b); 
    rect(x, y, 50, 25);
    if (dx < 0) { 
      triangle( x, y+25, x - 20, y, x, y); 
      rect(x, y - 20, 30, 30); 
      fill(0); 
      text(cargo, x + 10, y - 5); 
    } else { 
      triangle( x+49, y+25, x + 70, y, x+49, y); 
      rect(x+20, y - 20, 30, 30); 
      fill(0); 
      text(cargo, x + 30, y - 5); 
    } 
    stroke(0); 
    fill(0); 
    text(name, x, y + 15);  
  }
  void move() {
    x += dx;
    if (x < 25 || x > width - 75) dx *= -1;

    for (int i = 0; i < school.length; i++) { 
      if (dist(x, y, school[i].x, school[i].y) < 50) { 
        cargo += school[i].legs; 
        school[i].seaFloor();
      }
    }  
    
    if (x < 25) {
      caught += cargo;
      cargo = 0;
      score += caught;
    }
  }
}

class Sun {
  float x, y, dx;
  float r, g, b;
  
  Sun() {
    x = width*3/4;
    y = height/4 - 100;
    dx = 1;
    r = 255;
    g = 242;
    b = 98;
  }
  
  void show () {
    noStroke();
    fill(r, g, b);
    ellipse(x, y, 70, 70);
    stroke(0);
  }
  
  void move() {
    x += dx;
    if (x > width +200) x = 0;
  }
}
void keyPressed() {
  if (key == 'r') reset();
}
