import jsdom from 'jsdom';
import chai from 'chai';
import chaiImmutable from 'chai-immutable';

import ReactDOM_ from 'react-dom';

import AppContext from 'AppContext';

const document = jsdom.jsdom('<!doctype html><html><body></body></html>');
const window = document.defaultView;
const $_ = require('jquery')(window);
const moment_ = require('moment');

global.document = document;
global.window = window;

window.ReactDOM = ReactDOM_;
window.$ = $_;
window.jQuery = $_;
window.jquery = $_;
window.moment = moment_;

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
