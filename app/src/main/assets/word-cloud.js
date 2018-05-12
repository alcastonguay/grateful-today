  var text_string = "Love hope teaching change music sunshine health music talent hugs life gratitude generosity presence music art family music school learning colors rocks art food plumbing cleanliness teaching music love family friends  art family friends history organization";

  drawWordCloud(text_string);

  function drawWordCloud(text_string){

    var wordsString = app.loadData();

    var word_count = {};

    var words = wordsString.split(/[ '\-\(\)\*":;\[\]|{},.!?]+/);

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

    function googleColors(n) {
      var colors = ["#3366cc", "#dc3912", "#ff9900", "#109618", "#990099", "#0099c6", "#dd4477", "#66aa00", "#b82e2e", "#316395", "#994499", "#22aa99", "#aaaa11", "#6633cc", "#e67300", "#8b0707", "#651067", "#329262", "#5574a6", "#3b3eac"];
      return colors[n % colors.length];
    }


    var word_entries = d3.entries(word_count);

    var xScale = d3.scale.linear()
       .domain([1, d3.max(word_entries, function(d) {
          return d.value;
        })
       ])
       .range([25,75]);

    d3.layout.cloud().size([width, height])
      .timeInterval(20)
      .words(word_entries)
      .fontSize(function(d) { return xScale(+d.value); })
      .text(function(d) { return d.key; })
      .rotate(function() { return ~~(Math.random() * 1) * 90; })
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
          .style("fill", function(d, i) { return googleColors(i); })
          .attr("text-anchor", "middle")
          .attr("transform", function(d) {
            return "translate(" + [d.x, d.y] + ")rotate(" + d.rotate + ")";
          })
          .text(function(d) { return d.key; });
    }

    d3.layout.cloud().stop();
  }