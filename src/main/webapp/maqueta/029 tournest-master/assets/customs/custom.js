$(function() {

    class paquete {
        
        constructor( price , country ){
            this.price = price;
            this.country = country;
        }



        renderizar(){

            return ` <div class="col-md-4 col-sm-6">
													<div class="single-package-item">
														<img src="assets/images/packages/p1.jpg" alt="package-place">
														<div class="single-package-item-txt">
															<h3>${this.country} <span class="pull-right">${this.price}</span></h3>
															<div class="packages-para">
																<p>
																	<span>
																		<i class="fa fa-angle-right"></i> 3 Days 2 nights
																	</span>
																	<i class="fa fa-angle-right"></i>  5 star accomodation
																</p>
																<p>
																	<span>
																		<i class="fa fa-angle-right"></i>  transportation
																	</span>
																	<i class="fa fa-angle-right"></i> food facilities
																 </p>
															</div><!--/.packages-para-->
															<div class="packages-review">
																<p>
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																	<span>254 reviews</span>
																</p>
															</div><!--/.packages-review-->
															<div class="about-btn">
																<button  class="about-view packages-btn">
																	book now
																</button>
															</div><!--/.about-btn-->
														</div><!--/.single-package-item-txt-->
													</div><!--/.single-package-item-->

												</div>`

        }



    }


   $(document).ready(function () {
        ocultarGrilla();

        $('.travel-btn').click(function (e) { 
           consultarPaquetes();
            
        });
    });

 
    function ocultarGrilla(){
        $("#grilla-busqueda").hide();
    }

    function consultarPaquetes(){
        $.ajax({
            type: "GET",
            url: "http://127.0.0.1:81/paquetes/",
            data: "data",
            contentType: 'application/json',
            dataType: "json",
            headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
        },
            success: function (response) {
                   response.forEach(m => {
                    const maquete = new paquete(m.precio,m.destino);
                    $('#grilla-busqueda').append(paquete.renderizar());
                     });
            },
            error(e){
                alert(e);
            }
        });

    }

    function consultarViajes(params) {
        
    }

}); 

