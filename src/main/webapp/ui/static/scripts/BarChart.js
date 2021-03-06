var width = 480;
var height = 200;
function showBarGraph(divID,data){

        
		var div = d3.select("body").append("div").attr("class", "toolTip");

	    var axisMargin = 1,
	            margin = 1,
	            valueMargin = 1,
	            //width = parseInt(d3.select('body').style('width'), 10),
	           //height = parseInt(d3.select('body').style('height'), 10),
	            barHeight = (height-axisMargin-margin*2)* 0.85/data.length,
	            barPadding = (height-axisMargin-margin*2)*0.2/data.length,
	            data, bar, svg, scale, xAxis, labelWidth = 0;

	    max = d3.max(data, function(d) { return d.value; });
	    svg = d3.select("#"+divID)
	            .append("svg")
	            .attr("width", width)
	            .attr("height", height);


	    bar = svg.selectAll("g")
	            .data(data)
	            .enter()
	            .append("g");

	    bar.attr("class", "bar")
	            .attr("cx",0)
	            .attr("transform", function(d, i) {
	                return "translate(" + margin + "," + (i * (barHeight + barPadding) + barPadding) + ")";
	            });

	    bar.append("text")
	            .attr("class", "labelChart")
	            .attr("y", barHeight / 2)
	            .attr("dy", ".35em") //vertical align middle
	            .text(function(d){
	                return d.label;
	            }).each(function() {
	        labelWidth = Math.ceil(Math.max(labelWidth, this.getBBox().width));
	    });
	    
	    bar.on("click", function(d) {
	    	fnOpenSelectedSection(d.filter);
	    });

	    scale = d3.scaleLinear()
	            .domain([0, max])
	            .range([0, width - margin*2 - labelWidth]);

	    xAxis = d3.axisBottom()
	            .scale(scale)
	            .tickSize(-height + 2*margin + axisMargin);
	    
	 // no transition
	    /*bar.append("rect")
	            .attr("transform", "translate("+labelWidth+", 0)")
	            .attr("height", barHeight)
	            .attr("width", function(d){
	                return scale(d.value);
	            });*/
	    
	    // transition for all the bars at once
	    bar.append("rect")
        	.attr("transform", "translate("+labelWidth+", 0)")
        	.attr("height", barHeight)
        	.attr("width", 0)//this is the initial value
        	.transition()
        	.duration(1500)//time in ms
        	.attr("cursor", function(d){
        		return "pointer";
        	})
        	.attr("width", function(d){
        		return scale(d.value);
        		
        });
	    
	    // transition for each bar
	    /*bar.append("rect")
        	.attr("transform", "translate("+labelWidth+", 0)")
        	.attr("height", barHeight)
        	.attr("width", 0)
        	.transition()
        	.duration(1500)
        	.delay(function(d,i){ return i*250})//a different delay for each bar
        	.attr("width", function(d){
        		return scale(d.value);
        });*/

	    bar.append("text")
	            .attr("class", "value")
	            .attr("y", barHeight / 2)
	            .attr("dx", -valueMargin + labelWidth) //margin right
	            .attr("dy", ".35em") //vertical align middle
	            .attr("text-anchor", "end")
	            .text(function(d){
	                return (d.value);
	            })
	            .attr("x", function(d){
	                var width = this.getBBox().width;
	                return Math.max(width + valueMargin, scale(d.value));
	            });

	    bar.on("mousemove", function(d){
	                div.style("left", d3.event.pageX+10+"px");
	                div.style("top", d3.event.pageY-25+"px");
	                div.style("display", "inline-block");
	                div.html((d.label)+"<br>"+(d.value));
	            });
	    bar.on("mouseout", function(d){
	                div.style("display", "none");
	            });

	    svg.insert("g",":first-child")
	            .attr("class", "axisHorizontal")
	            .attr("transform", "translate(" + (margin + labelWidth) + ","+ (height - axisMargin - margin+8)+")")
	            .call(xAxis);
		}
	//});
	
	