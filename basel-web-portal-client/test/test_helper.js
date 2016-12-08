import jsdom from 'jsdom';
import chai from 'chai';
import chaiImmutable from 'chai-immutable';
import $ from 'jquery';

import AppContext from 'AppContext';

const document = jsdom.jsdom('<!doctype html><html><body></body></html>');
const window = document.defaultView;

global.document = document;
global.window = window;
window.$ = require('jquery')(window);


Object.keys(window).forEach((key) => {
    if (!(key in global)) {
      global[key] = window[key];
    }
  });

chai.use(chaiImmutable);

AppContext.setUser( {
            firstName: 'John', 
            lastName: 'Snow'
        });
