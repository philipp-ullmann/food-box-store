function country_code() {
  jQuery.ajax({ 
    url: '//freegeoip.net/json/', 
    type: 'POST', 
    dataType: 'jsonp',
    success: function(location) {
      console.log(location.country_code);
    }});
}

function countries() {
  jQuery.ajax({ 
    url: 'https://raw.githubusercontent.com/lukes/ISO-3166-Countries-with-Regional-Codes/master/all/all.json',
    type: 'GET', 
    dataType: 'json',
    success: function(countries) {
      var s = "";

      for (var i = 0; i < countries.length; i++) {
        var country = countries[i];
        s += '"' + country['alpha-2'] + '" "' + country['name'] + '"\n';
      }

      console.log(s);
    }});
}
