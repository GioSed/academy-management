import React from 'react';
import ReactDOM from 'react-dom/client'; // Import the createRoot API
import App from './App';

// Find the root element in your HTML
const rootElement = document.getElementById('root');

// Create a root and render the App
const root = ReactDOM.createRoot(rootElement);
root.render(
    <React.StrictMode>
        <App />
    </React.StrictMode>
);