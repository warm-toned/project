
$(function(){
	$("#"+i).hide();
});

function assess(id){
	 var color= $("#"+id).find("font").attr("color");
	 var score=0;
	 if(color=='#ccc'){
         for ( var i = 1; i <= id; i++) {
		  $("#"+i).find("font").attr("color","red");
		  score=i;
		 }
        // alert(score);
         $("#shopAssess").show();
     }else{    	 
    	 for ( var i = id; i <=5; i++) {
   		  $("#"+i).find("font").attr("color","#ccc");
   		  score=id;
   		 }
    	// alert(score+"fan");
     }
	 var colors= $("#1").find("font").attr("color");
	 if(colors=='#ccc'){
<<<<<<< HEAD
		 $("#shopAssess").hide();
=======
		 $("#shopAssess").attr("hidden","true")
>>>>>>> 8496e5393269c2c169025030d1a8c69bfd167a94
	 }
	 
}

function meu(i){
	if((i-1)==0){
	$("div[name="+0+"]").hide();
    $("textarea[name="+0+"]").hide();
	$("div[name="+i+"]").show();
	$("textarea[name="+i+"]").show();
	}else{
		$("div[name="+1+"]").hide();
	    $("textarea[name="+1+"]").hide();
		$("div[name="+i+"]").show();
		$("textarea[name="+i+"]").show();
	}
}

//function menuassess(id){
//	var color= $("#"+id).find("font").attr("color");
//	 var score=0;
//	 if(color=='#ccc'){
//        for ( var i = 1; i <= id; i++) {
//		  $("#"+i).find("font").attr("color","red");
//		  score=i;
//		 }
//        alert(score);
//        $("#menuAssess").show();
//    }else{    	 
//   	 for ( var i = id; i <=5; i++) {
//  		  $("#"+i).find("font").attr("color","#ccc");
//  		  score=id;
//  		 }
//   	// alert(score+"fan");
//    }
//	 var colors= $("#1").find("font").attr("color");
//	 if(colors=='#ccc'){
//		 $("#menuAssess").attr("hidden","true")
//	 }
//}