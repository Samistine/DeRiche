<%-- 
    Adjuster: Syed A.
--%>
<jsp:include page="includes/header.jsp">
    <jsp:param name="pageTitle" value="Body Check Form"/>
</jsp:include>
<main>
    <!-- <img src="image.jpg" alt="Sedon" width="450" height="200">  -->
    <div id="canvasDiv" style="position: relative; width: 673px; height: 614px;">
        <canvas id="layer2" width="673" height="514" style="position: absolute; top: 18px; left: 0px; border:2px solid;"></canvas>
        <canvas id="layer1" width="673" height="514" style="position: absolute; top: 18px; left: 0px; border:2px solid;"></canvas>
        <div style="position: absolute; top: 540px; left: 0px; width: 250px; height: 50px; border:2px solid;">
            <div style="position:absolute;top:4px;left:10px;">Color</div>
            <div style="position:absolute;top:24px;left:10px;width:15px;height:15px;background:black;" id="black" onclick="color(this)"></div>
            <div style="position:absolute;top:24px;left:25px;width:15px;height:15px;background:blue;" id="blue" onclick="color(this)"></div>
            <div style="position:absolute;top:4px;left:90px;">Eraser</div>
            <div style="position:absolute;top:24px;left:90px;width:15px;height:15px;background:white;border:1px solid;" id="white" onclick="color(this)"></div>
            <input type="button" value="Clear Picture" style="position:absolute; top:12px; left:150px; background:white; border:2px solid;" onclick="Pclear()">
        </div>
    </div>
    <form id="bodyForm" name="bodyForm" action="BodyCheckServlet" method="post" style="padding-top: 40px;">
        Patient First Name:<br>
        <input type="text" name="firstName"><br>
        Patient Last Name:<br>
        <input type="text" name="lastName"><br>
        Explain what happened:<br>
        <textarea name="what" rows="4" cols="50"></textarea><br>
        Where did it happen:<br>
        <input type="text" name="where"><br>
        When did it happen:<br>
        <input type="text" name="when"><br>
        Why did it happen:<br>
        <textarea name="why" rows="4" cols="50"></textarea><br>
        What did you do? Describe First Aid administered and follow up:<br>
        <textarea name="firstAid" rows="4" cols="50"></textarea><br>
        Recommendations for follow up: (Admin Only)<br>
        <textarea  name ="recommend" rows="4" cols="50"></textarea><br>
        <div id="sigDiv" style="position: relative; width: 710px; height: 300px;">
            <canvas id="sigLayer2" width="710" height="149" style="position:absolute; top:20px; left:0px; border:2px solid;"></canvas>
            <canvas id="sigLayer1" width="710" height="149" style="position:absolute; top:20px; left:0px; border:2px solid;"></canvas>
            <input type="button" value="Clear Signature" style="position:absolute; top:180px; left:0px; background:white; border:2px solid;" onclick="Sclear()">
        </div>
        <br><br>
        <input type="submit" onClick="save()" value="Submit"/>  <input type="reset" value="Clear"/>
        <input type="hidden" id="picURL" value="">
        <input type="hidden" name="sigURL" id="sigURL" value=""><br>
    </form><br>
    <button onclick="myFunction()">Help</button>
    <script>
        function myFunction() {
            alert(
                    "1.Mark on the chart below any abnormities observed\n2.Enter Participant name in the field provided (First name and last name) \n3.In the Explain what happened section, please type out how Participant received any abnormalities\n4.In the Where / When section, please type out Where & When Participant received any abnormalities\n5.In the What did you do? Describe First Aid administered and follow up section, please type out exactly what First Aid was administered and what steps were taken to follow up\n6.In the Recommendations for follow up section, only if you are an Admin, please type in any recommendations you have for the participant\n7.Enter your name in the field provided\n8.Sign the signature box with the mouse\n11.Select date from the Date Picker\n");
        }
    </script>
</main>
<script src="includes/bodycheck.js"></script>
<jsp:include page="includes/footer.jsp"/>