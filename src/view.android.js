import React, { Component } from 'react';
import { requireNativeComponent, Dimensions, NativeAppEventEmitter } from 'react-native';
import NativeListviewItem from './item';
const RNNativeListviewView = requireNativeComponent('NativeListviewView', null);

class NativeListview extends Component {
  constructor(props) {
    super(props)
    this._onScrollEnd = this._onScrollEnd.bind(this)
    this.onScrollListener = NativeAppEventEmitter.addListener('onScrollEnd', this._onScrollEnd)
  }

  componentWillUnmount() {
    this.props.renderRow = undefined;
    if (this.onScrollListener) this.onScrollListener.remove();
  }

  _onScrollEnd() {
    console.log('inside onScrollEnd in module')
    if (this.props.onScrollEnd) this.props.onScrollEnd()
  }

  render() {
    const { rowHeight, renderRow } = this.props;
    const height = Dimensions.get('window').height;
    const rowsToRender = Math.max(9, Math.round(height / rowHeight * 1.6));
    let items = [];
    for (let i = 0; i < rowsToRender; i++) {
      items.push(
        <NativeListviewItem
          rowID={i}
          type={2}
          renderRow={renderRow}
          key={'r_' + i}
        />
      );
    }

    return (
      <RNNativeListviewView {...this.props}>
        {items}
      </RNNativeListviewView>
    );
  }
}

NativeListview.propTypes = {
  numRows: React.PropTypes.number.isRequired,
  onScrollEnd: React.PropTypes.func,
  rowHeight: React.PropTypes.number.isRequired,
  renderRow: React.PropTypes.func
};

export default NativeListview;
