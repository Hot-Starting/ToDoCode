/** @type {import('next').NextConfig} */

const withPlugins = require("next-compose-plugins");
const withPWA = require("next-pwa");
const path = require("path");

const nextConfig = {
  reactStrictMode: true,  
};

module.exports = withPlugins(
  [
    [
      withPWA,
      {
        pwa: {
          dest: "public",
        },
      },
    ],
    [
      {
        sassOptions: {
          includePaths: [path.join(__dirname, 'styles')],
        }
      }
    ]
  ],
  nextConfig
);

