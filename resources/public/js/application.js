function select_current_country() {
  jQuery.ajax({ 
    url:      '//freegeoip.net/json/', 
    type:     'POST', 
    dataType: 'jsonp',

    success: function(location) {
      $('select#country').val(location.country_code);
    }});
}
