</div>

<!-- Footer Start-->
<div class="footer-copyright-area">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <div class="footer-copy-right">
                    <p></p>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Footer End-->
</div>
<!-- Chat Box End-->
<!-- jquery
            ============================================ -->
<script src="js/vendor/jquery-1.11.3.min.js"></script>
<!-- bootstrap JS
            ============================================ -->
<script src="js/bootstrap.min.js"></script>
<!-- meanmenu JS
            ============================================ -->
<script src="js/jquery.meanmenu.js"></script>
<!-- mCustomScrollbar JS
            ============================================ -->
<script src="js/jquery.mCustomScrollbar.concat.min.js"></script>
<!-- sticky JS
            ============================================ -->
<script src="js/jquery.sticky.js"></script>
<!-- scrollUp JS
            ============================================ -->
<script src="js/jquery.scrollUp.min.js"></script>
<!-- scrollUp JS
            ============================================ -->
<script src="js/wow/wow.min.js"></script>
<!-- counterup JS
            ============================================ -->
<script src="js/counterup/jquery.counterup.min.js"></script>
<script src="js/counterup/waypoints.min.js"></script>
<script src="js/counterup/counterup-active.js"></script>
<!-- jvectormap JS
            ============================================ -->
<script src="js/jvectormap/jquery-jvectormap-2.0.2.min.js"></script>
<script src="js/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<script src="js/jvectormap/jvectormap-active.js"></script>
<!-- peity JS
            ============================================ -->
<script src="js/peity/jquery.peity.min.js"></script>
<script src="js/peity/peity-active.js"></script>
<!-- sparkline JS
            ============================================ -->
<script src="js/sparkline/jquery.sparkline.min.js"></script>
<script src="js/sparkline/sparkline-active.js"></script>
<!-- flot JS
            ============================================ -->
<script src="js/flot/Chart.min.js"></script>
<script src="js/flot/dashtwo-flot-active.js"></script>
<!-- data table JS
            ============================================ -->
<script src="js/data-table/bootstrap-table.js"></script>
<script src="js/data-table/tableExport.js"></script>
<script src="js/data-table/data-table-active.js"></script>
<script src="js/data-table/bootstrap-table-editable.js"></script>
<script src="js/data-table/bootstrap-editable.js"></script>
<script src="js/data-table/bootstrap-table-resizable.js"></script>
<script src="js/data-table/colResizable-1.5.source.js"></script>
<script src="js/data-table/bootstrap-table-export.js"></script>
<!-- main JS
            ============================================ -->
<script src="js/main.js"></script>
<script>

    $("#crop_name").change(function () {
        var val = this.value;
        $.ajax({
            url: "get_crop_details.jsp",
            method: "POST",
            dataType: "json",
            data: {id: val},
            success: function (data) {
                $('#crop_rate').val(data.crop_rate);
                 $('#quantity').val(0);
            }
        })
    });
    $("#quantity").change(function () {
        var val = this.value;
        var rate =document.getElementById("crop_rate").value;
        $('#total_amount').val(val*rate);
    });
    function post_validation()
    {
        var quantity=document.getElementById("quantity").value;
        if(quantity==0)
        {
            alert('Please enter quantity');
            document.getElementById("quantity").focus();
            return false;
        }
        return true;
    }
</script>
</body>

</html>