  var text_string = "Love hope change music sunshine health music talent hugs life gratitude generosity presence music art family music school learning teaching colors rocks art food plumbing cleanliness teaching music love family friends music art family friends history organization";

  drawWordCloud(text_string);

  function drawWordCloud(text_string){


    var word_count = {};

    var words = text_string.split(/[ '\-\(\)\*":;\[\]|{},.!?]+/);

    words.forEach(function(word){
      var word = word.toLowerCase();
      if (word != "" && word.length>1){
        if (word_count[word]){
          word_count[word]++;
        } else {
          word_count[word] = 1;
        }
      }
    });


    var //margin = {top: 10, right: 10, bottom: 10, left: 10},
    width = window.innerWidth, //- margin.left - margin.right,
    height = window.innerHeight; //- margin.top - margin.bottom;

    var svg = d3.select("body").append("svg")
    .attr("width", width)
    .attr("height", height);

    var fill = d3.scale.category20();

    var word_entries = d3.entries(word_count);

    var xScale = d3.scale.linear()
       .domain([0, d3.max(word_entries, function(d) {
          return d.value;
        })
       ])
       .range([10,100]);

    d3.layout.cloud().size([width, height])
      .timeInterval(20)
      .words(word_entries)
      .fontSize(function(d) { return xScale(+d.value); })
      .text(function(d) { return d.key; })
      .rotate(function() { return ~~(Math.random() * 2) * 90; })
      .font("Impact")
      .on("end", draw)
      .start();

    function draw(words) {
      svg.append("g")
      .attr("transform", "translate(" + [width >> 1, height >> 1] + ")")
        .selectAll("text")
          .data(words)
        .enter().append("text")
          .style("font-size", function(d) { return xScale(d.value) + "px"; })
          .style("font-family", "Impact")
          .style("fill", function(d, i) { return fill(i); })
          .attr("text-anchor", "middle")
          .attr("transform", function(d) {
            return "translate(" + [d.x, d.y] + ")rotate(" + d.rotate + ")";
          })
          .text(function(d) { return d.key; });
    }

    d3.layout.cloud().stop();
  }