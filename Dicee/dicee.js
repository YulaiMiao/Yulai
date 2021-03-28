var randomnum1 = Math.floor(Math.random() * 6) + 1;

var img1source = "images/dice" + randomnum1 + ".png";

var img1 = document.querySelectorAll("img")[0];

img1.setAttribute("src", img1source) ;

var randomnum2 = Math.floor(Math.random() * 6) + 1;

var img2source = "images/dice" + randomnum2 + ".png";

var img2 = document.querySelectorAll("img")[1];

img2.setAttribute("src", img2source);

if (randomnum1 > randomnum2) {

    document.getElementById("result").innerHTML = "Player 1 wins" ;

}else if (randomnum2 > randomnum1){

    document.getElementById("result").innerHTML = "Player 2 wins" ;

}else{

    document.getElementById("result").innerHTML = "Draw" ;
}
